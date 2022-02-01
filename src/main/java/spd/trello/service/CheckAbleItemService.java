package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.CheckableItem;
import spd.trello.repository.CheckableItemDAO;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class CheckAbleItemService extends ServiceLayer<CheckableItem, CheckableItemDAO>{
    @Autowired
    public CheckAbleItemService(CheckableItemDAO repository) {
        super(repository);
    }
}
