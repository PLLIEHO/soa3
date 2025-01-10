package com.spo.entity.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum HairColor implements Serializable {
    GREEN("green"),
    BLACK("black"),
    BLUE("blue");

    private final String title;

    HairColor(String title) {
        this.title = title;
    }
}
