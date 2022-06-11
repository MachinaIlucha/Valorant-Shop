package com.shopvalorant.Model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date dateRegistered;
    private Date dateLastActive;
    private String email;
    private List<UserItems> items;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long getId() {
        return id;
    }

    @Transactional
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<UserItems> getItems() {
        return items;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    @Column(name = "date_registered")
    public Date getDateRegistered() {
        return dateRegistered;
    }

    @Column(name = "date_last_active")
    public Date getDateLastActive() {
        return dateLastActive;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public void setDateLastActive(Date dateLastActive) {
        this.dateLastActive = dateLastActive;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setItems(List<UserItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dateRegistered=" + dateRegistered +
                ", dateLastActive=" + dateLastActive +
                ", email='" + email + '\'' +
                ", items=" + items +
                '}';
    }
}
