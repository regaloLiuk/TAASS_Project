package com.example.mountbook_backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "shelter_services",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = true))
    private Shelter shelter;

    public Service() {}

    public Service(String name, float price, Shelter shelter) {
        this.name = name;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Float.compare(service.price, price) == 0 && id.equals(service.id) && Objects.equals(name, service.name) && Objects.equals(shelter, service.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, shelter);
    }
}
