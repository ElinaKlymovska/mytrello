package spd.trello.service;

import spd.trello.domain.Reminder;
import spd.trello.repository.IRepository;

public class ReminderService extends ServiceLayer<Reminder> {

    public ReminderService(IRepository<Reminder> repository) {
        super(repository);
    }

}
