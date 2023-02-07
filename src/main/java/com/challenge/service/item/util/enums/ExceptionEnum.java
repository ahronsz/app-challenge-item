package com.challenge.service.item.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    NOT_FOUND(0, "404", "Resource not found"),
    NOT_CONTENT(1, "204", "No content success");

    private final Integer id;
    private final String code;
    private final String description;
}
