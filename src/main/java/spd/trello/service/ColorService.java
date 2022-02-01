package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spd.trello.domain.Color;
import spd.trello.repository.ColorDAO;
import spd.trello.repository.IRepository;

@Service
public class ColorService extends ServiceLayer<Color, ColorDAO>{
    @Autowired
    public ColorService(ColorDAO repository) {
        super(repository);
    }

}
