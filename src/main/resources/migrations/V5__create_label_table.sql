CREATE TABLE labels
(
    id        UUID PRIMARY KEY,
    name VARCHAR(225) NOT NULL,
    color_id  UUID REFERENCES colors (id)
);