package com.challenge.service.item.business.impl;

import com.challenge.service.expose.dto.request.ItemRequest;
import com.challenge.service.expose.dto.request.UpdateItemRequest;
import com.challenge.service.expose.dto.response.ItemResponse;
import com.challenge.service.item.business.ClientService;
import com.challenge.service.item.business.ItemService;
import com.challenge.service.item.business.TypeService;
import com.challenge.service.item.mapper.ItemMapper;
import com.challenge.service.item.model.entity.Client;
import com.challenge.service.item.model.entity.Item;
import com.challenge.service.item.model.entity.Type;
import com.challenge.service.item.repository.ItemRepository;
import com.challenge.service.item.util.enums.StatusEnum;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ItemMapper mapper;

    @Override
    public Single<Item> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Single.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el Item");
                }));
    }

    @Override
    public Single<ItemResponse> getById(Long id) {
        return findById(id)
                .flatMap(item -> typeService.findById(item.getTypeId())
                        .flatMap(type -> clientService.findById(item.getClientId())
                                .map(client -> mapper.toResponse(client.getName(), type.getName(), item))));
    }

    @Override
    public Flowable<ItemResponse> getAll(String status) {
        if (status == null) {
            return findAll()
                    .observeOn(Schedulers.io())
                    .map(item -> {
                        Type type =  typeService.findById(item.getTypeId()).blockingGet();
                        Client client =  clientService.findById(item.getClientId()).blockingGet();
                        return mapper.toResponse(client.getName(), type.getName(), item);
                    });

        } else {
            return findAllByStatus(status)
                    .observeOn(Schedulers.io())
                    .map(item -> {
                        Type type =  typeService.findById(item.getTypeId()).blockingGet();
                        Client client =  clientService.findById(item.getClientId()).blockingGet();
                        return mapper.toResponse(client.getName(), type.getName(), item);
                    });
        }
    }

    @Override
    public Completable create(ItemRequest request) {
        return typeService.findByName(request.getTypeName())
                .flatMapCompletable(type -> clientService.findByName(request.getClientName())
                        .flatMapCompletable(client ->
                                repository.save(mapper.toSave(client.getId(), type.getId(), request)).ignoreElement()));
    }

    @Override
    public Completable createMany(Integer amount) {
        List<Integer> list = IntStream.range(1, amount).boxed().collect(Collectors.toList());
        Single.just(list)
                .flatMapPublisher(Flowable::fromIterable)
                .map(integer -> repository.save(mapper.toSaveRandom("Producto" + integer)).subscribe())
                .subscribe();

        repository.findAllByStatus("2")
                .zipWith(Flowable.interval(1, TimeUnit.SECONDS), (item, interval) -> item)
                .map(item -> {
                    item.setStatus("1");
                    return repository.save(item).subscribe();
                }).subscribe();

        return Completable.complete();
    }

    @Override
    public Completable update(Long id, UpdateItemRequest request) {
        return findById(id)
                .flatMapCompletable(item -> {
                    if (request.getTypeName() != null) {
                        return typeService.findByName(request.getTypeName())
                                .flatMapCompletable(type ->
                                        repository.save(mapper.toUpdate(item, request, type.getId()))
                                                .ignoreElement());
                    } else {
                        return repository.save(mapper.toUpdate(item, request, item.getId()))
                                .ignoreElement();
                    }
                });
    }

    @Override
    public Completable delete(Long id) {
        return findById(id)
                .map(item -> {
                    item.setStatus(StatusEnum.DELETED.getId());
                    return item;
                })
                .flatMapCompletable(item -> repository.save(item).ignoreElement());
    }

    private Flowable<Item> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró ningún item");
                }));
    }

    private Flowable<Item> findAllByStatus(String status) {
        return repository.findAllByStatus(StatusEnum.findByCode(status))
                .switchIfEmpty(Flowable.defer(() -> {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró ningúna Operación");
                }));
    }
}
