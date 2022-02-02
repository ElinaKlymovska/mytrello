package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.User;
import spd.trello.repository.IRepository;
@Service
public class UserService extends ServiceLayer<User>{
    public UserService(IRepository<User> repository) {
        super(repository);
    }
}
