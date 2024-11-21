package StaySure.Repositories.Entitys;

import java.util.Date;

public class Admin extends Person {
    Date DateToAdmin;
    Date FireToAdmin;

    public Admin(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate, String password,Date dateToAdmin, Date fireToAdmin) {
        super(id, name, lastName, phone, email, identificationNumber, birthDate, password);
        this.DateToAdmin = dateToAdmin;
        this.FireToAdmin = fireToAdmin;
    }

    //getters and setters

    public Date getDateToAdmin() {
        return DateToAdmin;
    }

    public void setDateToAdmin(Date dateToAdmin) {
        DateToAdmin = dateToAdmin;
    }

    public Date getFireToAdmin() {
        return FireToAdmin;
    }

    public void setFireToAdmin(Date fireToAdmin) {
        FireToAdmin = fireToAdmin;
    }
}
