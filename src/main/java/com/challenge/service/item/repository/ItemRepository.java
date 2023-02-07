package com.challenge.service.item.repository;

import com.challenge.service.item.model.entity.Item;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ItemRepository extends RxJava3CrudRepository<Item, Long> {
    Flowable<Item> findAllByStatus(String status);
}
