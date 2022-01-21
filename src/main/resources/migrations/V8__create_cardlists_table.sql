CREATE TABLE cardlists
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(225) NOT NULL,
    archived    boolean DEFAULT false,
    board_id    UUID REFERENCES boards (id),

    createdBy   VARCHAR(225) NOT NULL,
    updatedBy   VARCHAR(225) NOT NULL,
    createdDate timestamp without time zone NOT NULL,
    updatedDate timestamp without time zone NOT NULL
);