package com.challenge.service.item.business;

import com.challenge.service.expose.dto.response.TypeResponse;
import com.challenge.service.item.model.entity.Type;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface TypeService {
    Single<Type> findById(Long id);

    Single<TypeResponse> getById(Long id);

    Flowable<TypeResponse> getAll();

    Single<Type> findByName(String name);
}
