CREATE TABLE cards
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(225) NOT NULL,
    description VARCHAR(225) NOT NULL,
    archived    boolean      NOT NULL,
    reminder_id UUID REFERENCES reminders (id),
    cardlist_id UUID REFERENCES cardlists (id),

    createdBy   VARCHAR(225) NOT NULL,
    updatedBy   VARCHAR(225) NOT NULL,
    createdDate timestamp without time zone NOT NULL,
    updatedDate timestamp without time zone NOT NULL
);