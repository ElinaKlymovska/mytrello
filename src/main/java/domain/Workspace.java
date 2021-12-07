package domain;

import lombok.Data;

import java.util.List;

@Data
public class Workspace {
    private Long id;
    private String name;
    private List<Board> boards;
    private List<Member> members;
    private String description;
    private WorkspaceVisibility visibility;
}
