package com.spo.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PersonDTO {
    String birthday;
    String eyeColor;
    String hairColor;
    String nationality;
    LocationDTO location;
}
