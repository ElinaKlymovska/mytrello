package spd.trello.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Member {
    private UUID id;
    private User user;
    private RoleEnum role;
}
