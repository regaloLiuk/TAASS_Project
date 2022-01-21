package com.example.mountbook_backend.payload.request;

import java.util.Date;
import java.util.List;

public class OvernightStayRequest {
    private Long user;
    private Long bivouac;
    private int guests;
    private Date firstDay;
    private Date lastDay;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getBivouac() {
        return bivouac;
    }

    public void setBivouac(Long bivouac) {
        this.bivouac = bivouac;
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
