package com.example.VMSappdemo.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visitdto {
    @NotNull
    @Size(max=255)
    private String purpose;

    private String visitorName;

    private Userdto createdBy;

    @NotNull
    private String flatNumber;

    @NotNull
    private String idNumber;
    private Date inTime;

    private Date outTime;
    private String imageUrl;
    @NotNull
    private Integer noOfPeople;

}
