package com.example.VMSappdemo.Dto;

import com.example.VMSappdemo.Enums.Role;
import com.example.VMSappdemo.Enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Userdto {
    @NotNull
    @Size(max=255)
    private String name;
    @NotNull
    private String email;
    @NotNull
    @Size(max=255)
    private String phone;
    @NotNull
    private String idNumber;


    private Addressdto address;

    @NotNull
    private String role;


    private String flatNo;
    @NotNull
    private UserStatus status;

}
