package com.challenge.service.expose.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdateItemRequest {
    private String name;
    @Pattern(regexp = "(?i)^(BEBIDA|COMIDA|SALSAS|ESPECIAS)$")
    private String typeName;
    @Pattern(regexp = "(?i)^(100GR|1000GR)$")
    private String capacity;
    @Pattern(regexp = "(?i)^(BOTELLA|CAJA)$")
    private String container;
    private Boolean requireFridge;
}
