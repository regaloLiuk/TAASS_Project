package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_reservations",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true))
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "reserved_rooms",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = true))
    private List<Room> reservedRooms;
    private int guests;
    private Date firstDay;
    private Date lasttDay;

    public Reservation() {}

    public Reservation(User user, List<Room> reservedRooms, int guests, Date firstDay, Date lasttDay) {
        this.user = user;
        this.reservedRooms = reservedRooms;
        this.guests = guests;
        this.firstDay = firstDay;
        this.lasttDay = lasttDay;
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

    public List<Room> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(List<Room> reservedRooms) {
        this.reservedRooms = reservedRooms;
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

    public Date getLasttDay() {
        return lasttDay;
    }

    public void setLasttDay(Date lasttDay) {
        this.lasttDay = lasttDay;
    }
}
