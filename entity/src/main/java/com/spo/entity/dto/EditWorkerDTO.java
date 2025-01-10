package com.spo.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Data
public class EditWorkerDTO implements Serializable {
    private String name;
    private String creationDate;
    private String salary;
    private String startDate;
    private String endDate;
    private String status;
}
