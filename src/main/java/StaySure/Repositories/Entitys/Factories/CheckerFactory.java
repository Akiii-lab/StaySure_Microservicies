package StaySure.Repositories.Entitys.Factories;

import java.util.Date;

import StaySure.Repositories.Entitys.Checker;

public class CheckerFactory {

    public static Checker createChecker(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate,String  password, Date startToChecker) {
        return new Checker(id, name, lastName, phone, email, identificationNumber, birthDate, password,startToChecker);
    }
}
