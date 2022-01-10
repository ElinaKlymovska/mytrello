package spd.trello.service;

import spd.trello.domain.Resource;
import spd.trello.repository.IRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class ServiceLayer <T> {
    protected IRepository<T> repository;

    public ServiceLayer(IRepository<T> repository) {
        this.repository = repository;
    }

    public abstract T readById(UUID id);
    public abstract T create();
    public abstract void update(UUID id, T object);
    public abstract void delete (UUID id);
    public abstract List<T> getAll();

    public void print(T object){
        System.out.println(object);
    }

}
