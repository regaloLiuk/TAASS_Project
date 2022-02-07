package com.example.mountbook_backend.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OvernightStays implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Bivouac bivouac;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private int guests;

    private Date firstDay;

    private Date lastDay;


    public OvernightStays(){}

    public OvernightStays(Bivouac bivouac, User user, int guests, Date start, Date end){
        this.bivouac=bivouac;
        this.user=user;
        this.guests=guests;
        this.firstDay=start;
        this.lastDay=end;
    }

    public Long getId(){return this.id;}
    
    public Bivouac getBivouac(){return this.bivouac;}
    public void serBivouac(Bivouac bivouac){this.bivouac=bivouac;}
    
    public User getUser(){return this.user;}
    public void setUser(User user){this.user=user;}
    
    public int getGuest(){return this.guests;}
    public void setGuest(int guests){this.guests=guests;}
    
    public Date getFirstDay(){return this.firstDay;}
    public void setFirstDay(Date start){this.firstDay=start;}
    
    public Date getLastDay(){return this.lastDay;}
    public void setLastDay(Date end){this.lastDay=end;}


}
