package StaySure.Repositories.DAO;

import StaySure.Repositories.Entitys.Lesse;
import StaySure.DataBase.DatabaseConfig;
import java.sql.ResultSet;
import StaySure.Repositories.Entitys.Factories.LesseFactory;

public class LesseDao implements DaoBase<Lesse> {
    private final DatabaseConfig databaseConfig;
    private final String tableName = "lesses";
    private final String params = "name, last_name, phone, email, identification_number, birth_date, nit_lesse, password";

    private String getUserParams(Lesse lesse) {
        String res = "";

        res += "'" + lesse.getName() + "', '" + lesse.getLastName() + "', '" +
                lesse.getPhone() + "', '" + lesse.getEmail() + "', '" + lesse.getIdentificationNumber() + "', '"
                + lesse.getBirthDate() + "', '" + lesse.getNitLesse() + "', '" + lesse.getPassword() + "'";

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
            e.printStackTrace();
        }
        return null;
    }

    public LesseDao() {
        this.databaseConfig = new DatabaseConfig();
    }

    @Override
    public Lesse save(Lesse lesse) {

        try {
            long res = databaseConfig
                    .executeUpdate(
                            "INSERT INTO " + tableName + " (" + params + ") VALUES (" + getUserParams(lesse) + ")");
            if (res > 0) {
                Lesse resUser = lesse;
                resUser.setId(res);
                return resUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Lesse update(Lesse lesse) {
        try {
            String paramsSplited[] = params.split(", ");
            String valuesSplited[] = getUserParams(lesse).split(", ");
            String query = "";
            query += "UPDATE " + tableName + " SET ";
            for (int i = 0; i < paramsSplited.length; i++) {
                if (i == 0) {
                    query += paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                } else {
                    query += ", " + paramsSplited[i] + " = '" + valuesSplited[i] + "'";
                }
            }
            query += " WHERE id = " + lesse.getId();
            long res = databaseConfig
                    .executeUpdate(query);
            if (res > 0) {
                return lesse;
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

    public Lesse findByEmail(String email) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE email = '" + email + "'", Lesse.class);
            if (res.next()) {
                return getLesse(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Lesse findById(Long id) {
        try {
            ResultSet res = databaseConfig
                    .executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id, Lesse.class);
            if (res.next()) {
                return getLesse(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}