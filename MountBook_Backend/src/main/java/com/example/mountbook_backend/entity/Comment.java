package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Shelter shelter;

    // true -> positive | false -> negative
    private boolean service;
    private boolean clear;
    private boolean ospitality;
    private boolean food;
    private boolean location;
    //etc...


    public Comment() {
    }

    public Comment(User user, Shelter shelter) {
        this.user = user;
        this.shelter = shelter;
        this.clear=false;
        this.service=false;
        this.ospitality=false;
        this.food=false;
        this.location=false;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isOspitality() { return ospitality; }

    public void setOspitality(boolean ospitality) { this.ospitality = ospitality; }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }
}
