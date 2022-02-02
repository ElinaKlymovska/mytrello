package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Comment;
import spd.trello.repository.IRepository;
@Service
public class CommentService extends ServiceLayer<Comment>{
    public CommentService(IRepository<Comment> repository) {
        super(repository);
    }
}
