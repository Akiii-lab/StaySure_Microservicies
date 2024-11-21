package StaySure.Repositories.Entitys;

import java.util.Date;

public abstract class Person {
    private Long id;
    private String name;
    private String lastName;
    private Long phone;
    private String email;
    private Integer IdentificationNumber;
    private Date birthDate;
    private String password;

    public Person(Long id, String name, String lastName, Long phone, String email, Integer identificationNumber, Date birthDate, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        IdentificationNumber = identificationNumber;
        this.birthDate = birthDate;
        this.password = password;
    }

    // gettsers y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.IdentificationNumber = identificationNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
