package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spd.trello.domain.User;
import spd.trello.repository.UserDAO;

@Service
public class UserService extends ServiceLayer<User, UserDAO>{
    @Autowired
    public UserService(UserDAO repository) {
        super(repository);
    }
}
