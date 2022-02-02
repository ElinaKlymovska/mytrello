package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spd.trello.domain.Resource;
import spd.trello.exception.ResourceNotFoundException;
import spd.trello.service.CommonService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AbstractController< E extends Resource, S extends CommonService<E>>
        implements CommonController<E> {

    S service;

    public AbstractController(S service){
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<E> create(@RequestBody E resource) {
        E result = service.create(resource);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<E> update(@PathVariable UUID id, @RequestBody E resource) {
        E entity = service.readById(id);
        if (entity == null) throw new ResourceNotFoundException();
        resource.setCreatedBy("klymovska.elina@gmail.com");
        resource.setUpdatedBy("myfeatureknowlange@gmail.com");
        resource.setCreatedDate(LocalDateTime.now());
        resource.setUpdatedDate(LocalDateTime.now());
        E result = service.update(id, resource);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public HttpStatus delete(@PathVariable UUID id) {
        service.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<E> readById(@PathVariable UUID id) {
        E result = service.readById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public List<E> readAll() {
        return service.getAll();
    }
}
