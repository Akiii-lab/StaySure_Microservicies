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

    private ArrayList<QueryParam> getUserParams(Admin admin) {
        ArrayList<QueryParam> res = new ArrayList<>();

        res.add(new QueryParam("string", admin.getName()));
        res.add(new QueryParam("string", admin.getLastName()));
        res.add(new QueryParam("long", admin.getPhone()));
        res.add(new QueryParam("string", admin.getEmail()));
        res.add(new QueryParam("int", admin.getIdentificationNumber()));
        res.add(new QueryParam("date", admin.getBirthDate()));
        res.add(new QueryParam("date", admin.getDateToAdmin()));
        res.add(new QueryParam("date", admin.getFireToAdmin()));
        res.add(new QueryParam("string", admin.getPassword()));

        return res;
    }

    public AdminDao(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    private Admin getAdminFromRes(ResultSet res) {
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
    public Admin save(Admin admin) {

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
                            getUserParams(admin).toArray(new QueryParam[0]));
            if (res > 0) {
                Admin resUser = admin;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Admin update(Admin admin) {
        try {
            String paramsSplited[] = params.split(", ");
            ArrayList<QueryParam> valuesSplited = getUserParams(admin);
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = ?";
                } else {
                    query += ", " + paramsSplited[i] + " = ?";
                }
            }
            valuesSplited.add(new QueryParam("long", admin.getId()));
            query += " WHERE id = ? ";
            long res = databaseConfig
                    .executeUpdate(query, valuesSplited.toArray(new QueryParam[0]));
            if (res > 0) {
                return admin;
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

    public Admin findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = ?", new QueryParam("string", email));
            if (res.next()) {
                return getAdminFromRes(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Admin findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = ?", new QueryParam("long", id));
            if (res.next()) {
                return getAdminFromRes(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}