package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Date open;
    private Date close;
    private float altitude;
    private Long telephoneNumber;
    private String webSite;
    private String email;
    @OneToMany(mappedBy = "shelter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Service> services;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Outing> outings;


    public Shelter() {    }

    public Shelter(String name, String address, Date open, Date close, float altitude, Long telephoneNumber, String webSite, String email) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.altitude = altitude;
        this.telephoneNumber = telephoneNumber;
        this.webSite = webSite;
        this.email = email;
    }

    public Long getId() {return id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public Date getOpen() {return open;}
    public void setOpen(Date open) {this.open = open;}

    public Date getClose() {return close;}
    public void setClose(java.sql.Date close) {this.close = close;}

    public float getAltitude() {return altitude;}
    public void setAltitude(float altitude) {this.altitude = altitude;}

    public Long getTelephoneNumber() {return telephoneNumber;}
    public void setTelephoneNumber(Long telephoneNumber) {this.telephoneNumber = telephoneNumber;}

    public String getWebSite() {return webSite;}
    public void setWebSite(String webSite) {this.webSite = webSite;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Float.compare(shelter.altitude, altitude) == 0 &&
                id.equals(shelter.id) &&
                Objects.equals(name, shelter.name) &&
                Objects.equals(address, shelter.address) &&
                Objects.equals(open, shelter.open) &&
                Objects.equals(close, shelter.close) &&
                Objects.equals(telephoneNumber, shelter.telephoneNumber) &&
                Objects.equals(webSite, shelter.webSite) &&
                Objects.equals(email, shelter.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, open, close, altitude, telephoneNumber, webSite, email);
    }
}
