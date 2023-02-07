package com.challenge.service.item.business;

import com.challenge.service.expose.dto.request.ItemRequest;
import com.challenge.service.expose.dto.request.UpdateItemRequest;
import com.challenge.service.expose.dto.response.ItemResponse;
import com.challenge.service.item.model.entity.Item;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface ItemService {
    Single<Item> findById(Long id);

    Single<ItemResponse> getById(Long id);

    Flowable<ItemResponse> getAll(String status);

    Completable create(ItemRequest request);

    Completable createMany(Integer amount);

    Completable update(Long id, UpdateItemRequest request);

    Completable delete(Long id);
}
