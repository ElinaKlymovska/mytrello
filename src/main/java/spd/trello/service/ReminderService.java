package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Reminder;
import spd.trello.repository.IRepository;
import spd.trello.repository.ReminderDAO;

@Service
public class ReminderService extends ServiceLayer<Reminder, ReminderDAO> {
    @Autowired
    public ReminderService(ReminderDAO repository) {
        super(repository);
    }

}
