package StaySure.Repositories.Entitys.Factories;

import java.util.Date;

import StaySure.Repositories.Entitys.User;

public class UserFactory {

    public static User createUser(Long id, String name, String lastName, Long phone, String email,
            Integer identificationNumber,
            Date birthDate, Boolean notificationEnabled, String password) {
        return new User(id, name, lastName, phone, email, identificationNumber, birthDate, notificationEnabled,
                password);
    }
}

