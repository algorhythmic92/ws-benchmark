CREATE SCHEMA benchmark;

CREATE TABLE benchmark.exercise (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(64),
    variation VARCHAR(32),
    weight INTEGER,
    reps INTEGER,
    date_achieved DATE
);
