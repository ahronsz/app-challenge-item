package com.challenge.service.item.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    CREATED("1", "CREATED"),
    WAITING("2", "WAITING"),
    DELETED("3", "DELETED");

    private final String id;
    private final String code;

    public static String findById(String id) {
        for (StatusEnum value: values()) {
            if (Objects.equals(value.getId(), id)) {
                return value.getCode();
            }
        }
        return null;
    }

    public static String findByCode(String code) {
        for (StatusEnum value: values()) {
            if (Objects.equals(value.getCode().toLowerCase(), code.toLowerCase())) {
                return value.getId();
            }
        }
        return null;
    }
}
