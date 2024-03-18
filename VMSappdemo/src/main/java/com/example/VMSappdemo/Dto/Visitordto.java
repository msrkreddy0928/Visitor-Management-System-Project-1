package com.example.VMSappdemo.Dto;

import com.example.VMSappdemo.Entity.Address;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visitordto {
    @NotNull
    @Size(max=255)
    private  String name;
   @NotNull
   @Size(max=10)
    private String phone;
   @NotNull
   @Size(max=255)
    private String email;

    private Addressdto address;
    @NotNull
    @Size(max=255)
    private String idNumber;

}
