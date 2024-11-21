package StaySure.Repositories.Entitys.Factories;

import java.util.Date;

import StaySure.Repositories.Entitys.Admin;

public class AdminFactory {

    public static Admin createAdmin(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate,String  password,  Date startToAdmin, Date endToAdmin) {
        return new Admin(id, name, lastName, phone, email, identificationNumber, birthDate, password, startToAdmin, endToAdmin);
    }
}   
