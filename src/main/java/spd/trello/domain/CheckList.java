package spd.trello.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CheckList extends Resource{
    private String name;
    private List<CheckableItem> items;
    private UUID cardId;
}
