package com.trello.domain;

import lombok.Data;

@Data
public class CheckableItem {
    private Long id;
    private String name;
    private boolean isChecked;
}
