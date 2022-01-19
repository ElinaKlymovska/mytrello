package spd.trello.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends Resource{
    private Member member;
    private String text;
    private LocalDateTime localDateTime;
    private List<Attachment> attachments;
    private UUID cardId;
}
