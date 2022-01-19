package spd.trello.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CardList extends Resource{
    private String name;
    private List<Card> cards;
    private Boolean archived;
    private UUID boardId;
}
