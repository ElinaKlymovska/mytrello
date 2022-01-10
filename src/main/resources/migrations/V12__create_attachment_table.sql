CREATE TABLE attachments
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(225) NOT NULL,
    file        VARCHAR(100),

    comment_id  UUID REFERENCES comments (id),
    card_id     UUID REFERENCES cards (id),

    createdBy   VARCHAR(225) NOT NULL,
    updatedBy   VARCHAR(225) NOT NULL,
    createdDate timestamp without time zone NOT NULL,
    updatedDate timestamp without time zone NOT NULL
);