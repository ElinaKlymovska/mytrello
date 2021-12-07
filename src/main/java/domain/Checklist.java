package domain;

import lombok.Data;

import java.util.List;

@Data
public class Checklist {
    private Long id;
    private String name;
    private List<Checkableitem> items;
}
