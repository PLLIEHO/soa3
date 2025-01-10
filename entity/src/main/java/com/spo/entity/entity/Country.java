package com.spo.entity.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Country implements Serializable {
    FRANCE("france"),
    THAILAND("thailand"),
    SOUTH_KOREA("south_korea"),
    NORTH_KOREA("north_korea"),
    JAPAN("japan");

    private final String title;

    Country(String title) {
        this.title = title;
    }
}
