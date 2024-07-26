package ru.car.washing.dto;

import lombok.Data;

@Data
public class OfferDTO {

    private String hourOfStart;
    private Integer cost;
    private String name;
    private Boolean isConfirmed;



}
