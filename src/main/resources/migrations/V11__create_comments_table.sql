CREATE TABLE comments
(
    id            UUID PRIMARY KEY,
    member_id     UUID REFERENCES members (id),
    localDateTime timestamp without time zone NOT NULL,

    card_id    UUID REFERENCES cards (id),

    createdBy     VARCHAR(225) NOT NULL,
    updatedBy     VARCHAR(225) NOT NULL,
    createdDate   timestamp without time zone NOT NULL,
    updatedDate   timestamp without time zone NOT NULL
);