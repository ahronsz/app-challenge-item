package com.challenge.service.item.repository;

import com.challenge.service.item.model.entity.Type;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface TypeRepository extends RxJava3CrudRepository<Type, Long> {

    Maybe<Type> findByNameIgnoreCase(String name);
}
