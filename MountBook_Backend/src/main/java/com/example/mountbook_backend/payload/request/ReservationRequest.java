package com.example.mountbook_backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

public class ReservationRequest {

    @NotNull
    @NotEmpty
    private Long user;
    private Long structure;
    @NotNull
    @NotEmpty
    private int guests;
    @NotNull
    @NotEmpty
    private Date firstDay;
    @NotNull
    @NotEmpty
    private Date lastDay;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getStructure() {
        return structure;
    }

    public void setStruvture(Long structure) {
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
