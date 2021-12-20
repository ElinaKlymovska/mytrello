CREATE TABLE users
(
    id        UUID PRIMARY KEY,
    firstName VARCHAR(225) NOT NULL,
    lastName  VARCHAR(225) NOT NULL,
    email     VARCHAR(225) NOT NULL,
    timeZone  timestamp    NOT NULL
);