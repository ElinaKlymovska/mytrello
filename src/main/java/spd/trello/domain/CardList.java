package spd.trello.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CardList {
    private UUID id;
    private String name;
    private List<Card> cards;
    private Boolean archived;
}
