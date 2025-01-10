package com.spo.entity.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Status implements Serializable {
    FIRED("fired"),
    HIRED("hired"),
    RECOMMENDED_FOR_PROMOTION("recommended_for_promotion"),
    REGULAR("regular"),
    PROBATION("probation");

    private final String title;

    Status(String title) {
        this.title = title;
    }
}
