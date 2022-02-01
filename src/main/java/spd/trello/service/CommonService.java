package spd.trello.service;


import java.util.List;
import java.util.UUID;

public interface CommonService<E> {
    E create(E entity);
    E update(UUID id, E entity);
    void delete(UUID id);
    E readById(UUID id);
    List<E> getAll();
}
