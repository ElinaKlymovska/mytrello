package domain;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private Long id;
    private String name;
    private String description;
    private List<CardList> cardListList;
    private List<Member> members;
    private BoardVisibilityEnum visibility;
    private boolean favounteStatus;
    private boolean isArchived;
}
