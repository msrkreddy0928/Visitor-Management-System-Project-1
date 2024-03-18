package com.example.VMSappdemo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Integer httpStatus;
    private String exception;

    private String message;
}
