DROP TABLE IF EXISTS exercise;

CREATE TABLE IF NOT EXISTS exercises (
    id BIGINT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    variation VARCHAR(32) NOT NULL,
    reps INTEGER NOT NULL,
    weight INTEGER NOT NULL,
    date_achieved DATE NOT NULL
);

INSERT INTO exercises (id, name, variation, reps, weight, date_achieved)
VALUES
    (1, 'Flat Bench Press', 'Barbell', 10, 185, '2024-06-01'),
    (2, 'Incline Chest Fly', 'Dumbbell', 8, 40, '2024-06-02'),
    (3, 'Low Rows', 'Cable', 12, 120, '2024-06-03');