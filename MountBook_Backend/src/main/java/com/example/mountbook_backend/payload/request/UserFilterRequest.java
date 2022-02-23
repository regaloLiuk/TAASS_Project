package com.example.mountbook_backend.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class UserFilterRequest {

    int type; // 0:both, 1:shelter, 2:bivouac
    @NotEmpty
    @NotBlank
    @NotNull
    int guest;
    float minPrice;
    float maxPrice;
    Date dateStart;
    Date dateEnd;
    Boolean wifi;
    Boolean car;
    Boolean equipment;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getCar() {
        return car;
    }

    public void setCar(Boolean car) {
        this.car = car;
    }

    public Boolean getEquipment() {
        return equipment;
    }

    public void setEquipment(Boolean equipment) {
        this.equipment = equipment;
    }
}


