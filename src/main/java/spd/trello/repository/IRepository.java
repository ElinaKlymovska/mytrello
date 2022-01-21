package spd.trello.repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<E> {
	List<E>  getAll();
	E getById(UUID id);
	E save(E entity);
	E update(UUID id, E entity);
	void delete(UUID id);
}
