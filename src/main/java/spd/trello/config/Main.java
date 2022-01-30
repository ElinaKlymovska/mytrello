package spd.trello.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.service.WorkspaceService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
/*        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext
                (WebAppConfig.class, FlywayMigrator.class);

        WorkspaceService service = context.getBean(WorkspaceService.class);
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        Workspace demoworkspace = service.create(workspace);

        System.out.println(demoworkspace);
        context.close();*/
    }
}
