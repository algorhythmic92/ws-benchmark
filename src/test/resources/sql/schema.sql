CREATE TABLE IF NOT EXISTS exercises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    variation VARCHAR(32) NOT NULL,
    reps INTEGER NOT NULL,
    weight INTEGER NOT NULL,
    date_achieved DATE NOT NULL
);

INSERT INTO exercises (name, variation, reps, weight, date_achieved)
VALUES
    ('Flat Bench Press', 'Barbell', 10, 185, '2024-06-01'),
    ('Incline Chest Fly', 'Dumbbell', 8, 40, '2024-06-02'),
    ('Low Rows', 'Cable', 12, 120, '2024-06-03');