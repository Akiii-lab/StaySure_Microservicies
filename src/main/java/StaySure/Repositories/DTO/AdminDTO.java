package StaySure.Repositories.DTO;

import java.util.Date;

public class AdminDTO {
    public Long id;
    public String name;
    public String lastName;
    public Long phone;
    public String email;
    public Integer identificationNumber;
    public Date birthDate;
    public String password;
    public Date dateToAdmin;
    public Date fireToAdmin;
    

    public AdminDTO(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate, String password, Date dateToAdmin, Date fireToAdmin) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.birthDate = birthDate;
        this.password = password;
        this.dateToAdmin = dateToAdmin;
        this.fireToAdmin = fireToAdmin;
    }

}
