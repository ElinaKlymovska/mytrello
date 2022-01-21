package spd.trello.service;

import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public abstract class ServiceLayer <T> {
    protected IRepository<T> repository;

    public ServiceLayer(IRepository<T> repository) {
        this.repository = repository;
    }
    public  T readById(UUID id){return repository.getById(id);};
    public  T create(T object){return repository.save(object);};
    public  void update(UUID id, T object){repository.update(id, object);};
    public  void delete (UUID id){repository.delete(id);};
    public  List<T> getAll(){return repository.getAll();};

    public void print(T object){
        System.out.println(object);
    }

}
