package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Reminder;
import spd.trello.repository.IRepository;
@Service
public class ReminderService extends ServiceLayer<Reminder> {

    public ReminderService(IRepository<Reminder> repository) {
        super(repository);
    }

}
