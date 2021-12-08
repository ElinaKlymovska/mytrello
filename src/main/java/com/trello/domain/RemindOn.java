package com.trello.domain;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class RemindOn {
    private Long id;
    private long value;
    private TimeUnit timeUnit;
}
