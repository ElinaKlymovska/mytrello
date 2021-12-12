package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;


@Data
@EqualsAndHashCode(callSuper = true)
public class Attachment extends Resource{
    private String link;
    private String name;
    private File file;
}
