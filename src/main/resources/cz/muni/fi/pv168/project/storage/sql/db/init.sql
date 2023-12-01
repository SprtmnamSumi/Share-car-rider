CREATE TABLE IF NOT EXISTS "Category"
(
    `id`     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`   VARCHAR     NOT NULL UNIQUE,
    `name`   VARCHAR(50) NOT NULL,
    `colour` integer     NOT NULL
);
