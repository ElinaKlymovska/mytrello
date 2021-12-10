package spd.trello.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Label {
    private UUID id;
    private String name;
    private Color color;
}
