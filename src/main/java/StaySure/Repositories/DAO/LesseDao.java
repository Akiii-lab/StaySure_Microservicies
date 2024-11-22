package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.Lesse;
import StaySure.DataBase.DatabaseConfig;
import StaySure.DataBase.QueryParam;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import StaySure.Repositories.Entitys.Factories.LesseFactory;

@Component
public class LesseDao implements DaoBase<Lesse> {
    @Autowired
    private final DatabaseConfig databaseConfig;
    private final String tableName = "lesses";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, nit_lesse, password";

    private ArrayList<QueryParam> getUserParams(Lesse lesse) {
        ArrayList<QueryParam> res = new ArrayList<>();

        res.add(new QueryParam("string", lesse.getName()));
        res.add(new QueryParam("string", lesse.getLastName()));
        res.add(new QueryParam("long", lesse.getPhone()));
        res.add(new QueryParam("string", lesse.getEmail()));
        res.add(new QueryParam("int", lesse.getIdentificationNumber()));
        res.add(new QueryParam("date", lesse.getBirthDate()));
        res.add(new QueryParam("long", lesse.getNitLesse()));
        res.add(new QueryParam("string", lesse.getPassword()));

        return res;
    }

    private Lesse getLesse(ResultSet res) {
        try {
            return LesseFactory.createLesse(
                    res.getLong("id"),
                    res.getString("name"),
                    res.getString("last_name"),
                    res.getLong("phone"),
                    res.getString("email"),
                    res.getInt("identification_number"),
                    res.getDate("birth_date"),
                    res.getString("password"),
                    res.getLong("nit_lesse"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public LesseDao(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Lesse save(Lesse lesse) {

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
                            getUserParams(lesse).toArray(new QueryParam[0]));
            if (res > 0) {
                Lesse resUser = lesse;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Lesse update(Lesse lesse) {
        try {
            String paramsSplited[] = params.split(", ");
            ArrayList<QueryParam> valuesSplited = getUserParams(lesse);
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = ?";
                } else {
                    query += ", " + paramsSplited[i] + " = ?";
                }
            }
            valuesSplited.add(new QueryParam("long", lesse.getId()));
            query += " WHERE id = ? ";
            long res = databaseConfig
                    .executeUpdate(query, valuesSplited.toArray(new QueryParam[0]));
            if (res > 0) {
                return lesse;
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

    public Lesse findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = ?", new QueryParam("string", email));
            if (res.next()) {
                return getLesse(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesse findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = ?", new QueryParam("long", id));
            if (res.next()) {
                return getLesse(res);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}