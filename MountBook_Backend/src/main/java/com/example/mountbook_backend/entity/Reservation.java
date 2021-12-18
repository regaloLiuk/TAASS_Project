package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_reservation",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true))
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "reserved_room",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = true))
    private List<Room> roomsReserved;
    private int guests;
    private Date start;
    private Date end;

    public Reservation() {
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
}
