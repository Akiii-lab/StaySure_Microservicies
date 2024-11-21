package StaySure.Repositories.Entitys.Factories;

import java.util.Date;

import StaySure.Repositories.Entitys.Lesse;

public class LesseFactory {

    public static Lesse createLesse(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate,String password, Long nitLesse) {
        return new Lesse(id, name, lastName, phone, email, identificationNumber, birthDate,password,nitLesse);
    }
}
