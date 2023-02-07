package com.challenge.service.item.mapper;

import com.challenge.service.expose.dto.response.TypeResponse;
import com.challenge.service.item.model.entity.Type;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    TypeResponse toResponse(Type type);
}
