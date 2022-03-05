package com.example.mountbook_backend.payload.responce;

import java.util.Date;

public class ReservationResponse {

    private Long reservationId;
    private Long shelterId;
    private Long userId;
    private int guest;
    private Date firtsDay;
    private Date lastDay;

    public ReservationResponse() {
    }

    public ReservationResponse(Long reservationId, Long shelterId, Long userId, int guest, Date firtsDay, Date lastDay) {
        this.reservationId = reservationId;
        this.shelterId = shelterId;
        this.userId = userId;
        this.guest = guest;
        this.firtsDay = firtsDay;
        this.lastDay = lastDay;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public Date getFirtsDay() {
        return firtsDay;
    }

    public void setFirtsDay(Date firtsDay) {
        this.firtsDay = firtsDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }
}
