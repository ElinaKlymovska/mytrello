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

    @Override
    public CheckList readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public CheckList create(CheckList checkList) {
/*        CheckList checkList = new CheckList();
        checkList.setName("CheckList");
        checkList.setItems(new ArrayList<>());
        checkList.setCreatedBy("klymovska.elina@gmail.com");
        checkList.setUpdatedBy("myfeatureknowlange@gmail.com");
        checkList.setCreatedDate(LocalDateTime.now());
        checkList.setUpdatedDate(LocalDateTime.now());*/
        return repository.save(checkList);
    }

    @Override
    public void update(UUID id,CheckList checkList) {
        repository.update(id, checkList);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<CheckList> getAll() {
        return repository.getAll();
    }
}
