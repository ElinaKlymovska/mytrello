package spd.trello.domain;

import lombok.Data;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends Domain{
    private User createdBy;
    private User updatedBy;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;
}
