package com.challenge.service.item.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "Items")
@Getter
@Setter
@Builder
public class Item {
    @Id
    private Long id;
    @Column
    private Long clientId;
    @Column
    private Long typeId;
    @Column
    private String name;
    @Column
    private String container;
    @Column
    private String capacity;
    @Column
    private Boolean requireFridge;
    @Column
    private String status;
    @Column
    private LocalDateTime lastUpdate;
    @Column
    private LocalDateTime timestamp;
}
