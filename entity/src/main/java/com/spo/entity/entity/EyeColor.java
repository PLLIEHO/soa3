package com.spo.entity.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum EyeColor implements Serializable {
    GREEN ("green"),
    WHITE ("white"),
    BROWN ("brown");

    private final String title;

    EyeColor(String title) {
        this.title = title;
    }

}
