package com.spo.entity.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = true)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = true)
    private Float z;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min=1)
    private String name;

    public Location(Double x, Double y, Float z, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
}
