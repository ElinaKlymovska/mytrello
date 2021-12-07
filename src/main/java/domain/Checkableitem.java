package domain;

import lombok.Data;

@Data
public class Checkableitem {
    private Long id;
    private String name;
    private boolean checked;
}
