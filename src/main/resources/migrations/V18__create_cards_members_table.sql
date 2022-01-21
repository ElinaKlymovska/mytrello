CREATE TABLE cards_members
(
    card_id UUID NOT NULL REFERENCES cards(id),
    member_id  UUID NOT NULL REFERENCES members(id),
    CONSTRAINT cards_members_pk PRIMARY KEY (card_id, member_id)
);
