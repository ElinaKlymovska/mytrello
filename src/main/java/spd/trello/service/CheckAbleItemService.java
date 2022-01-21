package spd.trello.service;

import spd.trello.domain.CheckableItem;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class CheckAbleItemService extends ServiceLayer<CheckableItem>{
    public CheckAbleItemService(IRepository<CheckableItem> repository) {
        super(repository);
    }
}
