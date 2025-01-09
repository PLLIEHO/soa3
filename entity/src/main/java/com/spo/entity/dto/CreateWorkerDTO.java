package com.spo.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateWorkerDTO {
    private String name;
    private CoordinatesDTO coordinates;
    private String salary;
    private String startDate;
    private String endDate;
    private String status;
    private PersonDTO person;
}
