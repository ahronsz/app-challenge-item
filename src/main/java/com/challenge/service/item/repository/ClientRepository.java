package com.challenge.service.item.repository;

import com.challenge.service.item.model.entity.Client;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ClientRepository extends RxJava3CrudRepository<Client, Long> {
    Maybe<Client> findByNameIgnoreCase(String name);
}
