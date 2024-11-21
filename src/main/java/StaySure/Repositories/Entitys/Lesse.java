package StaySure.Repositories.Entitys;

import java.util.Date;

public class Lesse extends Person {
    private Long NitLesse;

    public Lesse(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate, String password, Long nitLesse) {
        super(id, name, lastName, phone, email, identificationNumber, birthDate, password);
        this.NitLesse = nitLesse;
    }

    //getters and Setters

    public Long getNitLesse() {
        return NitLesse;
    }

    public void setNitLesse(Long nitLesse) {
        NitLesse = nitLesse;
    }
}
