package com.example.VMSappdemo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AllVisitsBy {
    private List<Visitdto> visits;

    private Long totalRow;

    private Integer totalPage;
}
