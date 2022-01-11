package com.example.mountbook_backend.payload.request;

import com.example.mountbook_backend.entity.Outing;
import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.entity.Service;
import java.util.Date;
import java.util.List;

public class ShelterRequest {
    private String name;
    private String address;
    private Long telephoneNumber;
    private Date open;
    private Date close;
    private float altitude;
    private String webSite;
    private String email;
    private List<Room> rooms;
    private List<Service> services;
    private List<Outing> outings;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Date getOpen() {return open;}

    public void setOpen(Date open) {this.open = open;}

    public Date getClose() {return close;}

    public void setClose(Date close) {this.close = close;}

    public float getAltitude() {return altitude;}

    public void setAltitude(float altitude) {this.altitude = altitude;}

    public Long getTelephoneNumber() {return telephoneNumber;}

    public void setTelephoneNumber(Long telephoneNumber) {this.telephoneNumber = telephoneNumber;}

    public String getWebSite() {return webSite;}

    public void setWebSite(String webSite) {this.webSite = webSite;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public List<Room> getRooms() {return rooms;}

    public void setRooms(List<Room> rooms) {this.rooms = rooms;}

    public List<Service> getServices() {return services;}

    public void setServices(List<Service> services) {this.services = services;}

    public List<Outing> getOutings() {return outings;}

    public void setOutings(List<Outing> outings) {this.outings = outings;}
}
