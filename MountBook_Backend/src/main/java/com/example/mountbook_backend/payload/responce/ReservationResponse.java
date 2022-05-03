package com.example.mountbook_backend.payload.responce;

import java.util.Date;

public class ReservationResponse {

    private Long reservationId;
    private Long structureId;
    private Long userId;
    private int guest;
    private Date firstDay;
    private Date lastDay;
    private String name;
    private CommentResponse comments;

    public ReservationResponse() {
    }

    public ReservationResponse(Long reservationId, Long structureId, Long userId, int guest, Date firstDay, Date lastDay, String name) {
        this.reservationId = reservationId;
        this.structureId = structureId;
        this.userId = userId;
        this.guest = guest;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.name=name;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public CommentResponse getComments() {
        return comments;
    }

    public void setComments(CommentResponse comments) {
        this.comments = comments;
    }
}
