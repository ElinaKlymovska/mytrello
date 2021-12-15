package spd.trello.domain;

import lombok.Data;

import java.util.List;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Checklist extends Resource{
    private String name;
    private List<CheckableItem> items;
}
