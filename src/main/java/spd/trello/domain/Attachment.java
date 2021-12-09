package spd.trello.domain;

import lombok.Data;

import java.io.File;
import java.util.UUID;

@Data
public class Attachment {
    private UUID id;
    private String link;
    private String name;
    private File file;
}
