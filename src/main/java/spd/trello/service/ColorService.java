package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Color;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class ColorService extends ServiceLayer<Color>{
    public ColorService(IRepository<Color> repository) {
        super(repository);
    }

}
