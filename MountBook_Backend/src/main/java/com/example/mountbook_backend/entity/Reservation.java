package com.example.mountbook_backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shelter shelter;

    @Column(nullable = false)
    private int guests;

    @Column(columnDefinition = "DATE",nullable = false)
    private Date firstDay;

    @Column(columnDefinition = "DATE",nullable = false)
    private Date lastDay;

    public Reservation() {}

    public Reservation(User user, Shelter shelter, int guests, Date firstDay, Date lastDay) {
        this.user = user;
        this.shelter = shelter;
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

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
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
