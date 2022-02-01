package spd.trello.service;


import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public abstract class ServiceLayer<E , R extends IRepository<E>>
        implements CommonService<E>{
    R repository;

    public ServiceLayer(R repository){
        this.repository = repository;
    }

    @Override
    public E create(E entity) {
       return repository.save(entity);
    }

    @Override
    public E update(UUID id, E entity) {
        return repository.save(entity);
    }


    @Override
    public E readById (UUID id) {
        return repository.getById(id);
    }

    @Override
    public List<E> getAll() {
        return repository.getAll();
    }
    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
