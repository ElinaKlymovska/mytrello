package com.trello.domain;

import lombok.Data;

import java.util.List;

@Data
public class CardList {
    private Long id;
    private String name;
    private List<Card> cards;
    private boolean isArchived;
}
