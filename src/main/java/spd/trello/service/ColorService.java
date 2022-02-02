package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Color;
import spd.trello.repository.IRepository;

@Service
public class ColorService extends ServiceLayer<Color>{
    public ColorService(IRepository<Color> repository) {
        super(repository);
    }

}
