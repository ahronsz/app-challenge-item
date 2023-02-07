package com.challenge.service.expose.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ItemRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "(?i)^(BEBIDA|COMIDA|SALSAS|ESPECIAS)$")
    private String typeName;
    @NotBlank
    @Pattern(regexp = "(?i)^(100GR|1000GR)$")
    private String capacity;
    @NotBlank
    @Pattern(regexp = "(?i)^(BOTELLA|CAJA)$")
    private String container;
    @NotBlank
    private String clientName;
}
