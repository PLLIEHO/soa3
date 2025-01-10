package com.spo.entity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = true)
    @Min(-106)
    private Long x;

    @Column(nullable = true)
    private Float y;

    public Coordinates(Long x, Float y){
        this.x = x;
        this.y = y;
    }
}
