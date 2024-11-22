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

    private ArrayList<QueryParam> getDataParams(Lesse lesse) {
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

    private Lesse getData(ResultSet res) {
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
    public Lesse save(Lesse data) {

        try {
            long res = new DaoUtils().save(databaseConfig, getDataParams(data).toArray(new QueryParam[0]), params,
                    tableName);
            if (res > 0) {
                Lesse resData = data;
                resData.setId(res);
                return resData;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Lesse update(Lesse data) {
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

    public Lesse findByEmail(String email) {
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
    public Lesse findById(Long id) {
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