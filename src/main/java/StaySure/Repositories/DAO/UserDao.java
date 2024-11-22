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

    private ArrayList<QueryParam> getDataParams(User data) {
        ArrayList<QueryParam> res = new ArrayList<>();

        res.add(new QueryParam("string", data.getName()));
        res.add(new QueryParam("string", data.getLastName()));
        res.add(new QueryParam("long", data.getPhone()));
        res.add(new QueryParam("string", data.getEmail()));
        res.add(new QueryParam("int", data.getIdentificationNumber()));
        res.add(new QueryParam("date", data.getBirthDate()));
        res.add(new QueryParam("boolean", data.isNotificationsEnabled()));
        res.add(new QueryParam("string", data.getPassword()));

        return res;
    }

    private User getData(ResultSet res) {
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
    public User save(User data) {

        try {
            long res = new DaoUtils().save(databaseConfig, getDataParams(data).toArray(new QueryParam[0]),
                    tableName, params);
            if (res > 0) {
                User resData = data;
                resData.setId(res);
                return resData;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public User update(User data) {
        try {
            long res = new DaoUtils().update(databaseConfig, getDataParams(data), tableName, params,
                    data.getId());
            if (res > 0) {
                return findById(data.getId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            long res = new DaoUtils().detele(databaseConfig, tableName, id);
            return res > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User findByEmail(String email) {
        try {
            ResultSet res = new DaoUtils().getByStringColumn(databaseConfig, tableName, "email", email);
            if (res.next()) {
                return getData(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        try {
            ResultSet res = new DaoUtils().getByID(databaseConfig, tableName, id);
            if (res.next()) {
                return getData(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}