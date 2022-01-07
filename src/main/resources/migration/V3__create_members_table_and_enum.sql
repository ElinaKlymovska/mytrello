CREATE TYPE roles AS ENUM (
    'GUEST',
    'MEMBER',
    'ADMIN'
);

CREATE TABLE members
(
    id      UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users (id),
    roles roles DEFAULT 'GUEST'
);

