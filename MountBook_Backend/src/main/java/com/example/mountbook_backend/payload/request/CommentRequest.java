package com.example.mountbook_backend.payload.request;

import javax.validation.constraints.NotBlank;

public class CommentRequest {

    private Long user;
    private Long shelter;
    @NotBlank
    private boolean service;
    @NotBlank
    private boolean clear;
    @NotBlank
    private boolean ospitality;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getShelter() {
        return shelter;
    }

    public void setShelter(Long shelter) {
        this.shelter = shelter;
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
}
