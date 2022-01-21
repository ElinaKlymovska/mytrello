package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Board extends Resource {
    private String name;
    private String description;
    private List<CardList> cardLists = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private BoardVisibility visibility = BoardVisibility.PUBLIC;
    //private Boolean favourite; //TODO
    private Boolean archived = false;
    private UUID workspaceId;
}
