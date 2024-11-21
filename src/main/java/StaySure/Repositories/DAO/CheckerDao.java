package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.Checker;
import StaySure.DataBase.DatabaseConfig;
import java.sql.ResultSet;
import StaySure.Repositories.Entitys.Factories.CheckerFactory;

public class CheckerDao implements DaoBase<Checker> {
    private final DatabaseConfig databaseConfig;
    private final String tableName = "checkers";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, start_to_checker, password";

    private String getUserParams(Checker checker) {
        String res = "";

        res += "'" + checker.getName() + "', '" + checker.getLastName() + "', '" +
                checker.getPhone() + "', '" + checker.getEmail() + "', '" + checker.getIdentificationNumber() + "', '"
                + checker.getBirthDate() + "', '" + checker.getStartToChecker() + "', '" + checker.getPassword() + "'";

        return res;
    }

    private Checker getChecker(ResultSet res) {
        try {
            return CheckerFactory.createChecker(
                    res.getLong("id"),
                    res.getString("name"),
                    res.getString("last_name"),
                    res.getLong("phone"),
                    res.getString("email"),
                    res.getInt("identification_number"),
                    res.getDate("birth_date"),
                    res.getString("password"),
                    res.getDate("start_to_checker"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CheckerDao() {
        this.databaseConfig = new DatabaseConfig();
    }

    @Override
    public Checker save(Checker checker) {

        try {
            long res = databaseConfig
                    .executeUpdate(
                            "INSERT INTO " + tableName + " (" + params + ") VALUES (" + getUserParams(checker) + ")");
            if (res > 0) {
                Checker resUser = checker;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Checker update(Checker checker) {
        try {
            String paramsSplited[] = params.split(", ");
            String valuesSplited[] = getUserParams(checker).split(", ");
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                } else {
                    query += ", " + paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                }
            }
            query += " WHERE id = " + checker.getId();
            long res = databaseConfig
                    .executeUpdate(query);
            if (res > 0) {
                return checker;
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

    public Checker findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = '" + email + "'", Checker.class);
            if (res.next()) {
                return getChecker(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Checker findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id, Checker.class);
            if (res.next()) {
                return getChecker(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}