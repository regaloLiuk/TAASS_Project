package com.example.mountbook_backend.payload.responce;

import java.util.List;

public class UserHistoryResponse {

    private UserMinimalResponse user;
    private List<ReservationResponse> reservations;
    private List<CommentResponse> comments;

    public UserHistoryResponse(UserMinimalResponse user, List<ReservationResponse> reservations, List<CommentResponse> comments) {
        this.user = user;
        this.reservations = reservations;
        this.comments = comments;
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

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
