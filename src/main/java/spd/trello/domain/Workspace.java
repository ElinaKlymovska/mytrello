package spd.trello.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Workspace {
    private UUID id;
    private String name;
    private List<Board> boards;
    private List<Member> members;
    private String description;
    private WorkspaceVisibility visibility;
}
