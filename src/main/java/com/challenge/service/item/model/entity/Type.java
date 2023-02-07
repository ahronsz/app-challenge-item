package com.challenge.service.item.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Types")
@Getter
@Setter
@Builder
public class Type {
    @Id
    private Long id;
    @Column
    private String name;
}
