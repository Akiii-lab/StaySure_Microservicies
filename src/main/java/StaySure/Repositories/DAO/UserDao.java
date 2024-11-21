package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.User;
import StaySure.DataBase.DatabaseConfig;
import java.sql.ResultSet;
import StaySure.Repositories.Entitys.Factories.UserFactory;

public class UserDao implements DaoBase<User> {
    private final DatabaseConfig databaseConfig;
    private final String tableName = "users";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, notification_enabled, password";

    private String getUserParams(User user) {
        String res = "";

        res += "'" + user.getName() + "', '" + user.getLastName() + "', '" +
                user.getPhone() + "', '" + user.getEmail() + "', '" + user.getIdentificationNumber() + "', '"
                + user.getBirthDate() + "', '" + user.isNotificationsEnabled() + "', '" + user.getPassword() + "'";

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
            e.printStackTrace();
        }
        return null;
    }

    public UserDao() {
        this.databaseConfig = new DatabaseConfig();
    }

    @Override
    public User save(User user) {

        try {
            long res = databaseConfig
                    .executeUpdate(
                            "INSERT INTO " + tableName + " (" + params + ") VALUES (" + getUserParams(user) + ")");
            if (res > 0) {
                User resUser = user;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User update(User user) {
        try {
            String paramsSplited[] = params.split(", ");
            String valuesSplited[] = getUserParams(user).split(", ");
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                } else {
                    query += ", " + paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                }
            }
            query += " WHERE id = " + user.getId();
            long res = databaseConfig
                    .executeUpdate(query);
            if (res > 0) {
                User resUser = user;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            long res =  databaseConfig
                    .executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = '" + email + "'", User.class);
            if (res.next()) {
                return getUser(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id, User.class);
            if (res.next()) {
                return getUser(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}