package com.challenge.service.item.business.impl;

import com.challenge.service.expose.dto.response.ClientResponse;
import com.challenge.service.item.business.ClientService;
import com.challenge.service.item.mapper.ClientMapper;
import com.challenge.service.item.model.entity.Client;
import com.challenge.service.item.repository.ClientRepository;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public Single<Client> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Single.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el cliente");
                }));
    }

    @Override
    public Single<ClientResponse> getById(Long id) {
        return findById(id).map(mapper::toResponse);
    }

    @Override
    public Flowable<ClientResponse> getAll() {
        return findAll().map(mapper::toResponse);
    }

    @Override
    public Single<Client> findByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .switchIfEmpty(repository.save(Client.builder().name(name).build()));
    }

    private Flowable<Client> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró ningún cliente");
                }));
    }
}
