package spd.trello.service;

import spd.trello.domain.Color;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class ColorService extends ServiceLayer<Color>{
    public ColorService(IRepository<Color> repository) {
        super(repository);
    }

    @Override
    public Color readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public Color create(Color color) {
/*        Color color = new Color();
        color.setColor("F0F8FF");*/
        return repository.save(color);
    }

    @Override
    public void update(UUID id,Color color) {
        repository.update(id, color);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Color> getAll() {
        return repository.getAll();
    }
}
