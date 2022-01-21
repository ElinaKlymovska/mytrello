package spd.trello.service;

import spd.trello.domain.Comment;
import spd.trello.repository.IRepository;

public class CommentService extends ServiceLayer<Comment>{
    public CommentService(IRepository<Comment> repository) {
        super(repository);
    }
}
