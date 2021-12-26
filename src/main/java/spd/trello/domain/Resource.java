package spd.trello.domain;

import lombok.Data;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Resource extends Domain{
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;
}
