CREATE TYPE workspaceVisibility AS ENUM (
    'PRIVATE','PUBLIC'
    );

CREATE TABLE workspaces
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(225)                NOT NULL,
    description VARCHAR(225)                NOT NULL,
    visibility  workspaceVisibility DEFAULT 'PUBLIC',

    createdBy   VARCHAR(225)                NOT NULL,
    updatedBy   VARCHAR(225)                NOT NULL,
    createdDate timestamp without time zone NOT NULL,
    updatedDate timestamp without time zone NOT NULL
);