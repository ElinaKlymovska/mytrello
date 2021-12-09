package spd.trello.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Board {
    private UUID id;
    private String name;
    private String description;
    private List<CardList> cardLists;
    private List<Member> members;
    private BoardVisibility visibility;
    //private Boolean isFavourite; //TODO
    private Boolean archived;
}
