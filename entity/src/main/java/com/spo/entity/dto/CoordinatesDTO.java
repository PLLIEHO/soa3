package com.spo.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class CoordinatesDTO implements Serializable {
    String x;
    String y;
}
