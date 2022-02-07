package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Shelter shelter;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean wifi;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean equipment;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
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
