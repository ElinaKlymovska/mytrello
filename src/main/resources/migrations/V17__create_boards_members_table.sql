CREATE TABLE boards_members
(
    board_id UUID NOT NULL REFERENCES boards(id),
    member_id  UUID NOT NULL REFERENCES members(id),
    CONSTRAINT boards_members_pk PRIMARY KEY (board_id, member_id)
);
