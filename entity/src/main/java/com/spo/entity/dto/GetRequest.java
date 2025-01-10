package com.spo.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
public class GetRequest implements Serializable {
    private int pageSize;
    private int pageOffset;
    private List<String> sort;
    private List<String> filter;

}
