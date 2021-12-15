package spd.trello.domain;

import lombok.Data;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Reminder extends Resource{
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime remindOn;
    private Boolean acttive;
}
