package spd.trello.domain;

import lombok.Data;

import java.util.TimeZone;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private TimeZone timeZone;
}
