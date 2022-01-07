package spd.trello.repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<E> {
	List<E>  getAll();
	E getById(UUID id);
	void save(E entity);
	void update(UUID id, E entity);
	void delete(UUID id);
}
