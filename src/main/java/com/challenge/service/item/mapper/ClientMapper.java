package com.challenge.service.item.mapper;

import com.challenge.service.expose.dto.response.ClientResponse;
import com.challenge.service.item.model.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse toResponse(Client client);
}
