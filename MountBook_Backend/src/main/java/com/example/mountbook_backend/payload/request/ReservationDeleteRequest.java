package com.example.mountbook_backend.payload.request;

public class ReservationDeleteRequest {

    private Long userId;
    private Long reservationId;

    public Long getReservationId() { return reservationId; }

    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
