package spd.trello.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Comment {
    private UUID id;
    private Member member;
    private String text;
    private LocalDateTime localDateTime;
    private List<Attachment> attachments;
}
