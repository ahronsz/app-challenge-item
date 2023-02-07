package com.challenge.service.expose.web;

import com.challenge.service.expose.dto.request.ItemRequest;
import com.challenge.service.expose.dto.request.UpdateItemRequest;
import com.challenge.service.expose.dto.response.ItemResponse;
import com.challenge.service.item.business.ItemService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Completable create(@RequestBody @Valid ItemRequest request) {
        return service.create(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-many")
    public Completable createMany(@Valid @Null @Positive @Param("amount") Integer amount) {
        return service.createMany(amount);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Completable update(
            @Valid @NotNull @PathVariable("id") Long id,
            @RequestBody @Valid UpdateItemRequest request) {
        return service.update(id, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/delete/{id}")
    public Completable delete(
            @Valid @NotNull @PathVariable("id") Long id) {
        return service.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Single<ItemResponse> getById(@Valid @NotNull @PathVariable("id") Long id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Flowable<ItemResponse> getAll(
            @Valid @Null @Pattern(regexp = "(?i)^(WAITING|CREATED|DELETED)$") @Param("status") String status) {
        return service.getAll(status);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/exercise1")
    public List<String> exercise1() {
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            String s = "";
            if (i % 3 == 0) {
                s = "VIN";
            }
            if (i % 5 == 0) {
                s = s + "CLE";
            }
            list.add(s.isEmpty() ? String.valueOf(i) : s);
        }
        return list;
    }


}
