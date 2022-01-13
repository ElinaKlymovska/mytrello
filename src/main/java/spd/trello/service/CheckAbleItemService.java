package spd.trello.service;

import spd.trello.domain.CheckableItem;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class CheckAbleItemService extends ServiceLayer<CheckableItem>{
    public CheckAbleItemService(IRepository<CheckableItem> repository) {
        super(repository);
    }

    @Override
    public CheckableItem readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public CheckableItem create(CheckableItem checkableItem) {
/*        CheckableItem checkableItem = new CheckableItem();
        checkableItem.setName("FirstWorkspace");
        checkableItem.setCheckedSwitcher(true);*/
        return repository.save(checkableItem);
    }

    @Override
    public void update(UUID id,CheckableItem checkableItem) {
        repository.update(id, checkableItem);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<CheckableItem> getAll() {
        return repository.getAll();
    }
}
