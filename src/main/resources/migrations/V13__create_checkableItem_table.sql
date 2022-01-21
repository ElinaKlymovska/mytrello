CREATE TABLE checkableitems
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(225) NOT NULL,
    checkedSwitcher boolean      NOT NULL,
    cardlist_id    UUID REFERENCES cardlists (id)
);