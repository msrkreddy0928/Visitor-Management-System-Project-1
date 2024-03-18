package com.example.VMSappdemo.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Addressdto {

    @NotNull
    @Size(max=255)
    private String line1;
    @NotNull
    @Size(max=255)
    private String line2;
    @NotNull
    @Size(max=255)
    private String city;
    @NotNull
    @Size(max=255)
    private String pinCode;
    @NotNull
    @Size(max=255)
    private String country;
}
