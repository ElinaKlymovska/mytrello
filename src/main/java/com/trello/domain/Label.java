package com.trello.domain;

import lombok.Data;

@Data
public class Label {
    private Long id;
    private String name;
    private Color color;
}
