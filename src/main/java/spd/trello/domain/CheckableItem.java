package spd.trello.domain;

import lombok.Data;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CheckableItem extends Domain {
    private String name;
    private Boolean checkedSwitcher;
    private UUID cardListId;
}
