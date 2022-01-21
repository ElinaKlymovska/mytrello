package spd.trello.service;

import spd.trello.domain.CheckList;
import spd.trello.repository.IRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckListService extends ServiceLayer<CheckList>{
    public CheckListService(IRepository<CheckList> repository) {
        super(repository);
    }
}
