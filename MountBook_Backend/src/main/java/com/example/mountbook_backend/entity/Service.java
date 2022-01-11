package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Shelter shelter;

    private boolean wifi;
    private boolean equipment;
    private boolean car;

    public Service() {}

    public Service(Shelter shelter) {
        this.shelter = shelter;
    }

    public Long getId() {
        return id;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isEquipment() {
        return equipment;
    }

    public void setEquipment(boolean equipment) {
        this.equipment = equipment;
    }

    public boolean isCar() {
        return car;
    }

    public void setCar(boolean car) {
        this.car = car;
    }
}
