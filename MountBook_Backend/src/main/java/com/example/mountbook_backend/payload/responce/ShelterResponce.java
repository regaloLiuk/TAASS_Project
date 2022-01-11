package com.example.mountbook_backend.payload.responce;

import com.example.mountbook_backend.entity.Comment;
import com.example.mountbook_backend.entity.Outing;
import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.entity.Service;

import java.util.Date;
import java.util.List;

public class ShelterResponce {

    private String name;
    private String address;
    private Date open;
    private Date close;
    private float altitude;
    private Long telephoneNumber;
    private String webSite;
    private String email;
    private List<Room> rooms;
    private List<Service> services;
    private List<Outing> outings;
    private List<Comment> comments;

    public ShelterResponce() {    }

    public ShelterResponce(String name, String address, Date open, Date close, float altitude, Long telephoneNumber, String webSite, String email) {
        this.name = name;
        this.address = address;
        this.open = open;
        this.close = close;
        this.altitude = altitude;
        this.telephoneNumber = telephoneNumber;
        this.webSite = webSite;
        this.email = email;
    }
}
