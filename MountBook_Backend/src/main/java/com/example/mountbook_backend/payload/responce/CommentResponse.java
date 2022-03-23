package com.example.mountbook_backend.payload.responce;

public class CommentResponse {

    private Long reservationId;
    private Long shelterId;
    private Long bivouacId;
    private Long userId;
    private boolean service;
    private boolean clear;
    private boolean ospitality;
    private boolean food;
    private boolean location;

    public CommentResponse() {}

    public CommentResponse(Long reservationId, Long shelterId, Long bivouacId, Long userId, boolean service, boolean clear, boolean ospitality, boolean food, boolean location) {
        this.reservationId = reservationId;
        this.shelterId = shelterId;
        this.bivouacId = bivouacId;
        this.userId = userId;
        this.service = service;
        this.clear = clear;
        this.ospitality = ospitality;
        this.food = food;
        this.location = location;
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

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isOspitality() {
        return ospitality;
    }

    public void setOspitality(boolean ospitality) {
        this.ospitality = ospitality;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }
}
