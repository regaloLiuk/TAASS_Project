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
    private Long shelter;
    private Long bivouac;
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

    public Long getShelter() {
        return shelter;
    }

    public Long getBivouac() {
        return bivouac;
    }

    public void setBivouac(Long bivouac) {
        this.bivouac = bivouac;
    }

    public void setShelter(Long shelter) {
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
