package com.example.mountbook_backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Outing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "shelter_outings",
            joinColumns = @JoinColumn(name = "outing_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = true))
    private List<Shelter> nearShelters;

}
