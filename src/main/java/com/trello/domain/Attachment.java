package com.trello.domain;

import lombok.*;

import java.io.File;

@Data
public class Attachment {
    private Long id;
    private String link;
    private String name;
    private File file;
}
