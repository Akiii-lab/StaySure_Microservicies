package StaySure.Repositories.DTO;

import java.util.Date;

public class UserDTO {
    public Long id;
    public String name;
    public String lastName;
    public Long phone;
    public String email;
    public Integer identificationNumber;
    public Date birthDate;
    public String password;
    public Boolean notificationsEnabled;

    public UserDTO(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber,
            Date birthDate,
            String password, Boolean notificationsEnabled) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.birthDate = birthDate;
        this.password = password;
        this.notificationsEnabled = notificationsEnabled;
    }

    /*
     * json example
     * 
     * {
     * "id": 1,
     * "name": "John",
     * "lastName": "Doe",
     * "phone": 123456789,
     * "email": "jv3iO@example.com",
     * "identificationNumber": 123456789,
     * "birthDate": "1990-01-01",
     * "password": "password",
     * "notificationsEnabled": true
     * }
     */

}
