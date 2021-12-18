package com.example.mountbook_backend.entity;

import javax.persistence.*;

@Entity
public class Outing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "shelter_outing",
            joinColumns = @JoinColumn(name = "outing_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = true))
    private Shelter shelter;

    public Outing() {
    }

    public Outing(String name, String description, Shelter shelter) {
        this.name = name;
        this.description = description;
        this.shelter = shelter;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
