package spd.trello.service;

import spd.trello.domain.Color;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class ColorService extends ServiceLayer<Color>{
    public ColorService(IRepository<Color> repository) {
        super(repository);
    }

}
