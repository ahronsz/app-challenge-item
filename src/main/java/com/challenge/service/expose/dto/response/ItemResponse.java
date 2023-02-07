package com.challenge.service.expose.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {
    private String name;
    private String typeName;
    private String container;
    private String capacity;
    private Boolean requireFridge;
    private String lastUpdateDateTime;
    private String creationDateTime;
    private String clientName;
    private String status;
}
