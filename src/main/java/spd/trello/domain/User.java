package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Domain{
    private String firstName;
    private String lastName;
    private String email;
    private TimeZone timeZone;
}
