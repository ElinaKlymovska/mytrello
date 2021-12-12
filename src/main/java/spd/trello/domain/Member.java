package spd.trello.domain;

import lombok.Data;

import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends Domain{
    private User user;
    private RoleEnum role;
}
