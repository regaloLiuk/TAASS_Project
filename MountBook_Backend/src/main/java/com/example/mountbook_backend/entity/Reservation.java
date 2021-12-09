package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Shelter shelter;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Service> services;
    private int guests;
    private Date start;
    private Date end;

    public Reservation() {
    }

    public Reservation(User user, Shelter shelter, List<Service> services, int guests, Date start, Date end) {
        this.user = user;
        this.shelter = shelter;
        this.services = services;
        this.guests = guests;
        this.start = start;
        this.end = end;
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return guests == that.guests && id.equals(that.id) && user.equals(that.user) && shelter.equals(that.shelter) && Objects.equals(services, that.services) && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, shelter, services, guests, start, end);
    }
}
