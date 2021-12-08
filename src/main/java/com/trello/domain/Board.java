package com.trello.domain;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private Long id;
    private String name;
    private String description;
    private List<CardList> cardLists;
    private List<Member> members;
    private BoardVisibility visibility;
    private boolean isFavourite;
    private boolean isArchived;
}
