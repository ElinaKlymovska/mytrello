package spd.trello.domain;

import lombok.Data;

import java.util.List;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class   Workspace extends Resource{
    private String name;
    private String description;
    private WorkspaceVisibility visibility= WorkspaceVisibility.PRIVATE;
    private List<Member> members;
    private List<Board> boards;
}
