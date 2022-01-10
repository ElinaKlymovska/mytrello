package spd.trello.domain;

import lombok.Data;

import java.util.List;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class   Workspace extends Resource{
    private String name;
    private List<Board> boards;
    private List<Member> members;
    private String description;
    private WorkspaceVisibility visibility= WorkspaceVisibility.PRIVATE;
}
