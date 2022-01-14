package com.example.mountbook_backend.payload.request;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class ReservationRequest {
    private Long user;
    private List<Long> reservedRooms;
    private int guests;
    private Date firstDay;
    private Date lastDay;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public List<Long> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(List<Long> reservedRooms) {
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

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lasttDay) {
        this.lastDay = lasttDay;
    }
}
