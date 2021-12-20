package spd.trello.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Card extends Resource {
    private String name;
    private String description;
    private List<Member> assignedMembers = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();
    private List<Attachment> attachments = new ArrayList<>();
    private Boolean archived = false;
    private List<Comment> comments = new ArrayList<>();
    private Reminder reminder = new Reminder();
    private List<Checklist> checklists = new ArrayList<>();
}
