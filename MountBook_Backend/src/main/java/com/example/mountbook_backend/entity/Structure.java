package com.example.mountbook_backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Structure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String address;

    @Column(columnDefinition = "DATE", nullable = false)
    private Date open;

    @Column(columnDefinition = "DATE", nullable = false)
    private Date close;

    @Column(nullable = false)
    private int maxNumBed;

    private float price;    // il prezzo è riferito a persona

    @NotBlank
    private float altitude;

    @NotBlank
    private float longitude;

    @NotBlank
    private float latitude;

    @NotBlank
    private Long telephoneNumber;

    @NotBlank
    @Size(max = 120)
    private String webSite;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String description;

    private String image;   //link immagine, base64, da decidere

    private int type; //0 per rifugi, 1 per bivacchi

    public Structure() {}

    public Structure(String name, String address, int maxNumBed, Long telephoneNumber) {
        this.name = name;
        this.address = address;
        this.maxNumBed = maxNumBed;
        this.telephoneNumber = telephoneNumber;
    }

    public Long getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Date getOpen() {return open;}

    public void setOpen(Date open) {this.open = open;}

    public Date getClose() {return close;}

    public void setClose(Date close) {this.close = close;}

    public int getMaxNumBed() {
        return maxNumBed;
    }

    public void setMaxNumBed(int maxNumBed) {
        this.maxNumBed = maxNumBed;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAltitude() {return altitude;}

    public void setAltitude(float altitude) {this.altitude = altitude;}

    public float getLongitude() {return longitude;}

    public void setLongitude(float longitude) {this.longitude = longitude;}

    public float getLatitude() {return latitude;}

    public void setLatitude(float latitude) {this.latitude = latitude;}
   
    public Long getTelephoneNumber() {return telephoneNumber;}

    public void setTelephoneNumber(Long telephoneNumber) {this.telephoneNumber = telephoneNumber;}

    public String getWebSite() {return webSite;}

    public void setWebSite(String webSite) {this.webSite = webSite;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() { return type; }
}
