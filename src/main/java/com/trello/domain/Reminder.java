package com.trello.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reminder {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private RemindOn remindOn;
    private boolean isActtive;
}
