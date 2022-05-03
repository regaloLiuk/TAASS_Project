package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Structure structure;

    @Column(nullable = false)
    private int guests;

    @Column(columnDefinition = "DATE",nullable = false)
    private Date firstDay;

    @Column(columnDefinition = "DATE",nullable = false)
    private Date lastDay;

    public Reservation() {}

    public Reservation(User user, Structure structure, int guests, Date firstDay, Date lastDay) {
        this.user = user;
        this.structure = structure;
        this.guests = guests;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public Reservation(User user,int guests, Date firstDay, Date lastDay) {
        this.user = user;
        this.guests = guests;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
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

    public Structure getShelter() {
        return structure;
    }

    public void setShelter(Structure structure) {
        this.structure = structure;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }
}
