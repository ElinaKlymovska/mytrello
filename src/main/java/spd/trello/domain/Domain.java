package spd.trello.domain;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Domain {
    private UUID id = UUID.randomUUID();
}
