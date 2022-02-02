package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.CheckableItem;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class CheckAbleItemService extends ServiceLayer<CheckableItem>{
    public CheckAbleItemService(IRepository<CheckableItem> repository) {
        super(repository);
    }
}
