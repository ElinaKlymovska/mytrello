package spd.trello.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Checklist {
    private UUID id;
    private String name;
    private List<CheckableItem> items;
}
