package com.challenge.service.item.business.impl;

import com.challenge.service.expose.dto.response.TypeResponse;
import com.challenge.service.item.business.TypeService;
import com.challenge.service.item.mapper.TypeMapper;
import com.challenge.service.item.model.entity.Type;
import com.challenge.service.item.repository.TypeRepository;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Autowired
    private TypeMapper mapper;


    @Override
    public Single<Type> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Single.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el Tipo de Item");
                }));
    }

    @Override
    public Single<TypeResponse> getById(Long id) {
        return findById(id).map(mapper::toResponse);
    }

    @Override
    public Flowable<TypeResponse> getAll() {
        return findAll().map(mapper::toResponse);
    }

    @Override
    public Single<Type> findByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .switchIfEmpty(Single.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el Tipo de Item");}));

    }

    private Flowable<Type> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró ningún tipo de item");
                }));
    }
}
