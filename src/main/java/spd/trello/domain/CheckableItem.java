package spd.trello.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class CheckableItem {
    private UUID id;
    private String name;
    private Boolean checkedSwitcher;
}
