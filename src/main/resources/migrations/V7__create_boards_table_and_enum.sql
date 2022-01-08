CREATE TYPE boardVisibility AS ENUM (
     'PRIVATE', 'WORKSPACE', 'PUBLIC'
);

CREATE TABLE boards
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(225) NOT NULL,
    description  VARCHAR(225) NOT NULL,
    workspace_id UUID REFERENCES workspaces (id),
    visibility   boardVisibility DEFAULT 'PUBLIC',
    archived     boolean         DEFAULT false,

    createdBy    VARCHAR(225) NOT NULL,
    updatedBy    VARCHAR(225) NOT NULL,
    createdDate  timestamp without time zone NOT NULL,
    updatedDate  timestamp without time zone NOT NULL
);