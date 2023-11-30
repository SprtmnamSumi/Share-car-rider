--
-- Department table definition
--
CREATE TABLE IF NOT EXISTS "Category"
(
    `id`     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`   VARCHAR     NOT NULL UNIQUE,
    `name`   VARCHAR(10) NOT NULL UNIQUE,
    `colour` integer     NOT NULL
);
