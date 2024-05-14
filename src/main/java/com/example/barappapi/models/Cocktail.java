package com.example.barappapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cocktails")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String image_url;

    private String ingredients;

    private Float price_L;

    private Float price_M;

    private Float price_S;

    @ManyToMany(mappedBy = "cocktails")
    @JsonIgnore
    private List<Category> categories;
}
