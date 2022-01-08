package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.WorkspaceDAO;
import spd.trello.service.WorkspaceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkspaceTest extends BaseTest {

    private final WorkspaceService service;


    public WorkspaceTest() {
        service = new WorkspaceService(new WorkspaceDAO(dataSource));
    }

    @Test
    public void successCreate() {
        Workspace workspace = service.create();
        assertNotNull(workspace);
        assertAll(
                () -> assertNotNull(workspace.getId()),
                () -> assertEquals("FirstWorkspace", workspace.getName()),
                () -> assertEquals("My lovely project about jdbc,flyway,database", workspace.getDescription()),
                () -> assertEquals(WorkspaceVisibility.PUBLIC, workspace.getVisibility()),
                () -> assertEquals("klymovska.elina@gmail.com", workspace.getCreatedBy()),
                () -> assertEquals("myfeatureknowlange@gmail.com", workspace.getUpdatedBy()),
                () -> assertNotNull(workspace.getUpdatedDate()),
                () -> assertNotNull(workspace.getCreatedDate())
        );
    }

    @Test
    public void createFailure() {
        Workspace workspace = new Workspace();
        workspace.setName(null);
        assertThrows(Exception.class, () -> new WorkspaceDAO(dataSource).save(workspace));
    }

    @Test
    public void testFindById() {
        UUID id = service.create().getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create().getId();
        UUID id1 = service.create().getId();
        List<Workspace> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id), all.stream().filter(w -> w.getId().equals(id)).findFirst().orElse(null)),
                () -> assertEquals(service.readById(id1), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null)),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        Workspace initialWorkcpase = service.create();

        Workspace workspace = new Workspace();
        workspace.setName("TestWorkcpase");
        workspace.setDescription("Testing update");
        workspace.setVisibility(WorkspaceVisibility.PRIVATE);
        workspace.setUpdatedBy("testing@gmail.com");
        workspace.setCreatedBy(initialWorkcpase.getCreatedBy());
        workspace.setCreatedDate(initialWorkcpase.getCreatedDate());
        workspace.setUpdatedDate(LocalDateTime.now());
        service.update(initialWorkcpase.getId(),workspace);
        assertNotNull(workspace);
        assertAll(
                () -> assertEquals("TestWorkcpase", workspace.getName()),
                () -> assertEquals("Testing update", workspace.getDescription()),
                () -> assertEquals(WorkspaceVisibility.PRIVATE, workspace.getVisibility()),
                () -> assertEquals("klymovska.elina@gmail.com", workspace.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", workspace.getUpdatedBy()),
                () -> assertNotNull(workspace.getUpdatedDate()),
                () -> assertNotNull(workspace.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        Workspace initialWorkcpase = service.create();
        Workspace workspace = new Workspace();
        workspace.setName(null);
        workspace.setDescription(null);
        assertThrows(Exception.class,()->service.update(initialWorkcpase.getId(),workspace));
    }


    @Test
    public void successDelete() {
        UUID id = service.create().getId();
        assertEquals(service.readById(id),service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
        service.delete(id);
        assertEquals(null,service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }

}
