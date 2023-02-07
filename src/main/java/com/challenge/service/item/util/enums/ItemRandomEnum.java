package com.challenge.service.item.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Random;

public class ItemRandomEnum {

    @Getter
    @AllArgsConstructor
    public enum Capacity {
        _100GR(0, "100gr"),
        _1000GR(1, "1000gr");

        private final Integer id;
        private final String code;

        public static String getType() {
            Integer random = new Random().nextInt(2);
            for (Capacity value: values()) {
                if (Objects.equals(value.getId(), random)) {
                    return value.getCode();
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Container {
        BOTELLA(0, "Botella"),
        CAJA(1, "Caja");

        private final Integer id;
        private final String code;

        public static String getType() {
            Integer random = new Random().nextInt(2);
            for (Container value: values()) {
                if (Objects.equals(value.getId(), random)) {
                    return value.getCode();
                }
            }
            return null;
        }
    }
}
