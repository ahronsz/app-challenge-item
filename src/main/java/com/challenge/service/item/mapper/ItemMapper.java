package com.challenge.service.item.mapper;

import com.challenge.service.expose.dto.request.ItemRequest;
import com.challenge.service.expose.dto.request.UpdateItemRequest;
import com.challenge.service.expose.dto.response.ItemResponse;
import com.challenge.service.item.model.entity.Item;
import com.challenge.service.item.util.constants.Format;
import com.challenge.service.item.util.enums.ItemRandomEnum;
import com.challenge.service.item.util.enums.StatusEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Mapper(componentModel = "spring",
        imports = {Random.class, LocalDateTime.class, StatusEnum.class, ItemRandomEnum.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ItemMapper {


    ItemResponse toResponse(String clientName, String typeName, Item item);

    @Mapping(target = "requireFridge", expression = "java(new Random().nextBoolean())")
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(StatusEnum.CREATED.getId())")
    Item toSave(Long clientId, Long typeId, ItemRequest request);

    @Mapping(target = "clientId", expression = "java(1L)")
    @Mapping(target = "typeId", expression = "java((long) (new Random().nextInt(4) + 1))")
    @Mapping(target = "capacity", expression = "java(ItemRandomEnum.Capacity.getType())")
    @Mapping(target = "container", expression = "java(ItemRandomEnum.Container.getType())")
    @Mapping(target = "requireFridge", expression = "java(new Random().nextBoolean())")
    @Mapping(target = "status", expression = "java(StatusEnum.WAITING.getId())")
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    Item toSaveRandom(String name);

    @Mapping(source = "request.name", target = "name")
    @Mapping(source = "request.capacity", target = "capacity")
    @Mapping(source = "request.container", target = "container")
    @Mapping(source = "request.requireFridge", target = "requireFridge")
    @Mapping(target = "status", expression = "java(StatusEnum.WAITING.getId())")
    @Mapping(target = "lastUpdate", expression = "java(LocalDateTime.now())")
    Item toUpdate(@MappingTarget Item item, UpdateItemRequest request, Long typeId);



    @AfterMapping
    default void afterMapping(Item item, @MappingTarget ItemResponse response) {
        Locale spanishLocale = new Locale("es", "ES");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Format.FULL_DATE_TIME, spanishLocale);

        response.setLastUpdateDateTime(item.getLastUpdate() != null ? item.getLastUpdate().format(dateTimeFormatter) : "");
        response.setCreationDateTime(item.getTimestamp().format(dateTimeFormatter));
        response.setStatus(StatusEnum.findById(response.getStatus()));
    }





}
