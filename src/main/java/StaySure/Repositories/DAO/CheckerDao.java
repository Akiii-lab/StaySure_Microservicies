package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.Checker;
import StaySure.DataBase.DatabaseConfig;
import StaySure.DataBase.QueryParam;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import StaySure.Repositories.Entitys.Factories.CheckerFactory;

@Component
public class CheckerDao implements DaoBase<Checker> {
    @Autowired
    private final DatabaseConfig databaseConfig;
    private final String tableName = "checkers";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, start_to_checker, password";

    private ArrayList<QueryParam> getUserParams(Checker checker) {
        ArrayList<QueryParam> res = new ArrayList<>();

        res.add(new QueryParam("string", checker.getName()));
        res.add(new QueryParam("string", checker.getLastName()));
        res.add(new QueryParam("long", checker.getPhone()));
        res.add(new QueryParam("string", checker.getEmail()));
        res.add(new QueryParam("int", checker.getIdentificationNumber()));
        res.add(new QueryParam("date", checker.getBirthDate()));
        res.add(new QueryParam("date", checker.getStartToChecker()));
        res.add(new QueryParam("string", checker.getPassword()));

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
            System.out.println(e.getMessage());
        }
        return null;
    }

    public CheckerDao(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Checker save(Checker checker) {

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
                            getUserParams(checker).toArray(new QueryParam[0]));
            if (res > 0) {
                Checker resUser = checker;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Checker update(Checker checker) {
        try {
            String paramsSplited[] = params.split(", ");
            ArrayList<QueryParam> valuesSplited = getUserParams(checker);
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = ?";
                } else {
                    query += ", " + paramsSplited[i] + " = ?";
                }
            }
            valuesSplited.add(new QueryParam("long", checker.getId()));
            query += " WHERE id = ? ";
            long res = databaseConfig
                    .executeUpdate(query, valuesSplited.toArray(new QueryParam[0]));
            if (res > 0) {
                return checker;
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

    public Checker findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = ?", new QueryParam("string", email));
            if (res.next()) {
                return getChecker(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Checker findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = ?", new QueryParam("long", id));
            if (res.next()) {
                return getChecker(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}