package spd.trello.config;

public class Main {
    public static void main(String[] args) {
        DataBaseConfiguration.startMigration();
/*        WorkspaceService workspaceService = new WorkspaceService(new WorkspaceDAO(DataBaseConfiguration.getDataSource()));
        workspaceService.create();
        Workspace updated_workspace = new Workspace();
        updated_workspace.setId(null);
        updated_workspace.setName(null);
        updated_workspace.setDescription("new challenges");
        updated_workspace.setVisibility(WorkspaceVisibility.PRIVATE);
        updated_workspace.setCreatedBy("klymovska.elina@gmail.com");
        updated_workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        updated_workspace.setCreatedDate(LocalDateTime.now());
        updated_workspace.setUpdatedDate(LocalDateTime.now());
        workspaceService.update(workspaceService.create().getId(),updated_workspace);*/

/*        BoardService boardService = new BoardService();
        boardService.create();
        Board updatedBoard = new Board();
        updatedBoard.setName("NewBoard");
        updatedBoard.setDescription("updating");
        updatedBoard.setVisibility(BoardVisibility.PRIVATE);
        updatedBoard.setArchived(true);
        updatedBoard.setCreatedBy("klymovska.elina@gmail.com");
        updatedBoard.setUpdatedBy("myfeatureknowlange@gmail.com");
        updatedBoard.setCreatedDate(LocalDateTime.now());
        updatedBoard.setUpdatedDate(LocalDateTime.now());
        boardService.update(boardService.create().getId(),updatedBoard);*/

    }
}
