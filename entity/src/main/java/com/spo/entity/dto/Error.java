package com.spo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class Error implements Serializable {
        int error_code;
        List<String> error_message;

}
