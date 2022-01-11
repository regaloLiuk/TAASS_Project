package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int beds;
    private float price;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Shelter shelter;

    public Room() {}

    public Room(String description, int beds, float price) {
        this.description = description;
        this.beds = beds;
        this.price = price;
    }


    /*
    * {"description":"room 101",
    *  "bed" : 2,
    *  "price" : 22.4}
    * */

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

}
