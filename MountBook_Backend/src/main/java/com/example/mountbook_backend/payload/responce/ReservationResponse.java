package com.example.mountbook_backend.payload.responce;

import java.util.Date;

public class ReservationResponse {

    private Long reservationId;
    private Long shelterId;
    private Long bivouacId;
    private Long userId;
    private int guest;
    private Date firstDay;
    private Date lastDay;

    public ReservationResponse() {
    }

    public ReservationResponse(Long reservationId, Long shelterId, Long bivouacId, Long userId, int guest, Date firstDay, Date lastDay) {
        this.reservationId = reservationId;
        this.shelterId = shelterId;
        this.bivouacId = bivouacId;
        this.userId = userId;
        this.guest = guest;
        this.firstDay = firstDay;
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

    public Long getBivouacId() {
        return bivouacId;
    }

    public void setBivouacId(Long bivouacId) {
        this.bivouacId = bivouacId;
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
