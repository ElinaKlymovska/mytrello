CREATE TABLE boardTemplates
(
    id      UUID PRIMARY KEY,
    board_id    UUID REFERENCES boards (id)
);

CREATE TABLE cardTemplates
(
    id      UUID PRIMARY KEY,
    card_id UUID NOT NULL REFERENCES cards (id)
);
