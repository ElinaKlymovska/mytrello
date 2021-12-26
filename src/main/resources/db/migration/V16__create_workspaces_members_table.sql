CREATE TABLE workspaces_members
(
    workspace_id UUID NOT NULL REFERENCES workspaces(id),
    member_id  UUID NOT NULL REFERENCES members(id),
    CONSTRAINT workspaces_members_pk PRIMARY KEY (workspace_id, member_id)
);
