package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.Admin;
import StaySure.DataBase.DatabaseConfig;
import StaySure.DataBase.QueryParam;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import StaySure.Repositories.Entitys.Factories.AdminFactory;

@Component
public class AdminDao implements DaoBase<Admin> {
    @Autowired
    private final DatabaseConfig databaseConfig;
    private final String tableName = "admins";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, date_to_admin, fire_to_admin, password";

    private ArrayList<QueryParam> getDataParams(Admin data) {
        ArrayList<QueryParam> res = new ArrayList<>();

        res.add(new QueryParam("string", data.getName()));
        res.add(new QueryParam("string", data.getLastName()));
        res.add(new QueryParam("long", data.getPhone()));
        res.add(new QueryParam("string", data.getEmail()));
        res.add(new QueryParam("int", data.getIdentificationNumber()));
        res.add(new QueryParam("date", data.getBirthDate()));
        res.add(new QueryParam("date", data.getDateToAdmin()));
        res.add(new QueryParam("date", data.getFireToAdmin()));
        res.add(new QueryParam("string", data.getPassword()));

        return res;
    }

    public AdminDao(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    private Admin getData(ResultSet res) {
        try {
            return AdminFactory.createAdmin(
                    res.getLong("id"),
                    res.getString("name"),
                    res.getString("last_name"),
                    res.getLong("phone"),
                    res.getString("email"),
                    res.getInt("identification_number"),
                    res.getDate("birth_date"),
                    res.getString("password"),
                    res.getDate("date_to_admin"),
                    res.getDate("fire_to_admin"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Admin save(Admin data) {

        try {
            long res = new DaoUtils().save(databaseConfig, getDataParams(data).toArray(new QueryParam[0]), params,
                    tableName);
            if (res > 0) {
                Admin resData = data;
                resData.setId(res);
                return resData;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Admin update(Admin data) {
        try {
            long res = new DaoUtils().update(databaseConfig, getDataParams(data), params, tableName,
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

    public Admin findByEmail(String email) {
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
    public Admin findById(Long id) {
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