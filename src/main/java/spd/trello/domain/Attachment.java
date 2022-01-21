package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
public class Attachment extends Resource{
    private String link;
    private String name;
    private File file;
    private UUID commentId;
    private UUID cardId;
}
