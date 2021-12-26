CREATE TABLE reminders
(
    id          UUID PRIMARY KEY,
    start_data  timestamp without time zone NOT NULL,
    end_data    timestamp without time zone NOT NULL,
    remindOn    timestamp without time zone NOT NULL,
    acttive     boolean DEFAULT false,
    createdBy   VARCHAR(225) NOT NULL,
    updatedBy   VARCHAR(225) NOT NULL,
    createdDate timestamp without time zone NOT NULL,
    updatedDate timestamp without time zone NOT NULL

);