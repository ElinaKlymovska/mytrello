package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.CheckList;
import spd.trello.repository.CheckListDAO;
import spd.trello.repository.IRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CheckListService extends ServiceLayer<CheckList, CheckListDAO>{
    @Autowired
    public CheckListService(CheckListDAO repository) {
        super(repository);
    }
}
