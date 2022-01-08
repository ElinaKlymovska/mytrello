CREATE TABLE cards_labels
(
    card_id UUID NOT NULL REFERENCES cards(id),
    label_id  UUID NOT NULL REFERENCES labels(id),
    CONSTRAINT cards_labels_pk PRIMARY KEY (card_id, label_id)
);
