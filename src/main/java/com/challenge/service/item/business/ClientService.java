package com.challenge.service.item.business;

import com.challenge.service.expose.dto.response.ClientResponse;
import com.challenge.service.item.model.entity.Client;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface ClientService {
    Single<Client> findById(Long id);

    Single<ClientResponse> getById(Long id);

    Flowable<ClientResponse> getAll();

    Single<Client> findByName(String name);
}
