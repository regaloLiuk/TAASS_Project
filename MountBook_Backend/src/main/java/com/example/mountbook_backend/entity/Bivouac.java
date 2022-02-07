package com.example.mountbook_backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Bivouac{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    private int bed;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isOpen;

    @Column(columnDefinition = "TEXT DEFAULT ''")
    private String description;


    public Bivouac() {
    }

    public Bivouac(String name, int bed) {
        this.name = name;
        this.bed = bed;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


