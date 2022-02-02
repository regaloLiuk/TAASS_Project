package com.example.mountbook_backend.payload.request;

import java.sql.Date;

public class ShelterFilterRequest {

    float minPrice;
    float maxPrice;
    Date dateStart;
    Date dateEnd;
    Boolean wifi;
    Boolean car;
    Boolean equipment;

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


