package com.example.mountbook_backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @NotNull
    @NotEmpty
    private Long user;

    private Long shelter;

    private Long bivouac;

    @NotBlank
    @NotNull
    private boolean service;

    @NotBlank
    @NotNull
    private boolean clear;

    @NotBlank
    @NotNull
    private boolean ospitality;

    @NotBlank
    @NotNull
    private boolean food;

    @NotBlank
    @NotNull
    private boolean location;



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

    public Long getBivouac() {
        return bivouac;
    }

    public void setBivouac(Long bivouac) {
        this.bivouac = bivouac;
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
