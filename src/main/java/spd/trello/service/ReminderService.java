package spd.trello.service;

import spd.trello.domain.Reminder;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class ReminderService extends ServiceLayer<Reminder> {

    public ReminderService(IRepository<Reminder> repository) {
        super(repository);
    }

    @Override
    public Reminder readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public Reminder create(Reminder reminder) {
        return repository.save(reminder);
    }

    @Override
    public void update(UUID id,Reminder reminder) {
        repository.update(id, reminder);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Reminder> getAll() {
        return repository.getAll();
    }
}
