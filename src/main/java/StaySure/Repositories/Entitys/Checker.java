package StaySure.Repositories.Entitys;

import java.util.Date;

public class Checker extends Person {
    private Date StartToChecker;

    public Checker(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate, String password, Date startToChecker) {
        super(id, name, lastName, phone, email, identificationNumber, birthDate, password);
        this.StartToChecker = startToChecker;
    }

    //getters and setters

    public Date getStartToChecker() {
        return StartToChecker;
    }

    public void setStartToChecker(Date startToChecker) {
        this.StartToChecker = startToChecker;
    }
}
