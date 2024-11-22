package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.User;
import StaySure.DataBase.DatabaseConfig;
import StaySure.DataBase.QueryParam;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import StaySure.Repositories.Entitys.Factories.UserFactory;

@Component
public class UserDao implements DaoBase<User> {
    @Autowired
    private final DatabaseConfig databaseConfig;
    private final String tableName = "users";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, notification_enabled, password";

    private ArrayList<QueryParam> getUserParams(User user) {
        ArrayList<QueryParam> res = new ArrayList<>();

        res.add(new QueryParam("string", user.getName()));
        res.add(new QueryParam("string", user.getLastName()));
        res.add(new QueryParam("long", user.getPhone()));
        res.add(new QueryParam("string", user.getEmail()));
        res.add(new QueryParam("int", user.getIdentificationNumber()));
        res.add(new QueryParam("date", user.getBirthDate()));
        res.add(new QueryParam("boolean", user.isNotificationsEnabled()));
        res.add(new QueryParam("string", user.getPassword()));

        return res;
    }

    private User getUser(ResultSet res) {
        try {
            return UserFactory.createUser(
                    res.getLong("id"),
                    res.getString("name"),
                    res.getString("last_name"),
                    res.getLong("phone"),
                    res.getString("email"),
                    res.getInt("identification_number"),
                    res.getDate("birth_date"),
                    res.getBoolean("notification_enabled"),
                    res.getString("password"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public UserDao(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public User save(User user) {

        try {
            String paramToPrepare = "";
            for (int i = 0; i < params.split(", ").length; i++) {
                if (i == 0) {
                    paramToPrepare += "?";
                } else {
                    paramToPrepare += ", ?";
                }
            }
            long res = databaseConfig
                    .executeUpdate(
                            "INSERT INTO " + tableName + " (" + params + ") VALUES (" + paramToPrepare + ")",
                            getUserParams(user).toArray(new QueryParam[0]));
            if (res > 0) {
                User resUser = user;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public User update(User user) {
        try {
            String paramsSplited[] = params.split(", ");
            ArrayList<QueryParam> valuesSplited = getUserParams(user);
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = ?";
                } else {
                    query += ", " + paramsSplited[i] + " = ?";
                }
            }
            valuesSplited.add(new QueryParam("long", user.getId()));
            query += " WHERE id = ? ";
            long res = databaseConfig
                    .executeUpdate(query, valuesSplited.toArray(new QueryParam[0]));
            if (res > 0) {
                User resUser = user;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            long res = databaseConfig
                    .executeUpdate("DELETE FROM " + tableName + " WHERE id = ?", new QueryParam("long", id));
            return res > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = ?", new QueryParam("string", email));
            if (res.next()) {
                return getUser(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = ?", new QueryParam("long", id));
            if (res.next()) {
                return getUser(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}