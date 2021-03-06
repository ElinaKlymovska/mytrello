package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Label extends Domain {
    private String name;
    private Color color;
}
