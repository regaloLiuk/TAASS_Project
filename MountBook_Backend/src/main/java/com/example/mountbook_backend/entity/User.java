package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private int age;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String name;
    private String lastName;

    public User() {}

    public User(int age, String email, String password, String name, String lastName) {
        this.age = age;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {return id;}
    public int getAge() {return age;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getLastName() {return lastName;}

    public void setAge(int age) {this.age = age;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
               id.equals(user.id) &&
               email.equals(user.email) &&
               password.equals(user.password) &&
               name.equals(user.name) &&
               lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, email, password, name, lastName);
    }
}
