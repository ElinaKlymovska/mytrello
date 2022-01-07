CREATE TABLE checkableitems
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(225) NOT NULL,
    checkedSwitcher boolean      NOT NULL,
    cardlist_id    UUID REFERENCES cardlists (id),
    createdBy       VARCHAR(225) NOT NULL,
    updatedBy       VARCHAR(225) NOT NULL,
    createdDate     timestamp without time zone NOT NULL,
    updatedDate     timestamp without time zone NOT NULL
);