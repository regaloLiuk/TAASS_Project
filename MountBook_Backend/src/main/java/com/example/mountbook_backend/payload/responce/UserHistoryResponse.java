package com.example.mountbook_backend.payload.responce;

import java.util.List;

public class UserHistoryResponse {

    private UserMinimalResponse user;
    private List<ReservationResponse> reservations;

    public UserHistoryResponse(UserMinimalResponse user, List<ReservationResponse> reservations) {
        this.user = user;
        this.reservations = reservations;
    }

    public UserMinimalResponse getUserId() {
        return user;
    }

    public void setUserId(UserMinimalResponse user) {
        this.user = user;
    }

    public List<ReservationResponse> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationResponse> reservations) {
        this.reservations = reservations;
    }

}
