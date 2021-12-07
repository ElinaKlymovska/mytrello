package domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private User user;
    private RoleEnum role;
}
