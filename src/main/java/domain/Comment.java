package domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    private Long id;
    private Member member;
    private String text;
    private LocalDateTime localDateTime;
    private List<Attachment> attachments;
}
