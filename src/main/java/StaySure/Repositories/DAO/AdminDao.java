package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.Admin;
import StaySure.DataBase.DatabaseConfig;
import java.sql.ResultSet;
import StaySure.Repositories.Entitys.Factories.AdminFactory;

public class AdminDao implements DaoBase<Admin> {
    private final DatabaseConfig databaseConfig;
    private final String tableName = "admins";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, date_to_admin, fire_to_admin, password";

    private String getUserParams(Admin admin) {
        String res = "";

        res += "'" + admin.getName() + "', '" + admin.getLastName() + "', '" +
                admin.getPhone() + "', '" + admin.getEmail() + "', '" + admin.getIdentificationNumber() + "', '"
                + admin.getBirthDate() + "', '" + admin.getDateToAdmin() + "', '" + admin.getFireToAdmin() + "', '"
                + admin.getPassword() + "'";

        return res;
    }

    public AdminDao() {
        this.databaseConfig = new DatabaseConfig();
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin save(Admin admin) {

        try {
            long res = databaseConfig
                    .executeUpdate(
                            "INSERT INTO " + tableName + " (" + params + ") VALUES (" + getUserParams(admin) + ")");
            if (res > 0) {
                Admin resUser = admin;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Admin update(Admin admin) {
        try {
            String paramsSplited[] = params.split(", ");
            String valuesSplited[] = getUserParams(admin).split(", ");
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                } else {
                    query += ", " + paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                }
            }
            query += " WHERE id = " + admin.getId();
            long res = databaseConfig
                    .executeUpdate(query);
            if (res > 0) {
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            long res = databaseConfig
                    .executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Admin findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = '" + email + "'", Admin.class);
            if (res.next()) {
                return getAdminFromRes(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id, Admin.class);
            if (res.next()) {
                return getAdminFromRes(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}