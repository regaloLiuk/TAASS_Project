package com.example.mountbook_backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Host {

    @Id
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;

    public Host() {}

    public Host(String email,String password, String name, String lastName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {return id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return id.equals(host.id) && email.equals(host.email) && password.equals(host.password) && Objects.equals(name, host.name) && Objects.equals(lastName, host.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, lastName);
    }
}
