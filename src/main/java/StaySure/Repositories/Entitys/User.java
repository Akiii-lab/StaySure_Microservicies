package StaySure.Repositories.Entitys;

import java.util.Date;

public class User extends Person {

    private Boolean notificationEnabled;

    public User(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber,
            Date birthDate, Boolean notificationEnabled, String password) {
        super(id, name, lastName, phone, email, identificationNumber, birthDate, password);
        this.notificationEnabled = notificationEnabled;
    }

    // gettsers y setters

    public Boolean isNotificationsEnabled() {
        return this.notificationEnabled;
    }

    public void setnotificationsEnabled(Boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }
}