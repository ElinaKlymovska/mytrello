package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Comment;
import spd.trello.repository.CommentDAO;
import spd.trello.repository.IRepository;
@Service
public class CommentService extends ServiceLayer<Comment, CommentDAO>{
    @Autowired
    public CommentService(CommentDAO repository) {
        super(repository);
    }
}
