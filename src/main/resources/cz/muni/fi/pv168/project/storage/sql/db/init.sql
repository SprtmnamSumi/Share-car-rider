CREATE TABLE IF NOT EXISTS "Category"
(
    `id`     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`   VARCHAR     NOT NULL UNIQUE,
    `name`   VARCHAR(50) NOT NULL,
    `colour` integer     NOT NULL
);

CREATE TABLE IF NOT EXISTS "Currency"
(
    `id`                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`               VARCHAR          NOT NULL UNIQUE,
    `name`               VARCHAR          NOT NULL,
    `symbol`             VARCHAR          NOT NULL,
    `newestRateToDollar` DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS "Template"
(
    `id`                           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    `guid`                         VARCHAR          NOT NULL UNIQUE,
    `currencyId`                   BIGINT REFERENCES "Currency" (`id`),
    `title`                        VARCHAR          NOT NULL,
    `description`                  VARCHAR          NOT NULL,
    `distance`                     DOUBLE PRECISION NOT NULL,
    `fuelConsumption`              DOUBLE PRECISION NOT NULL,
    `costOfFuelPerLitre`           DOUBLE PRECISION NOT NULL,
    `numberOfPassengers`           integer          NOT NULL,
    `commission`                   DOUBLE PRECISION NOT NULL,
    `categoryId`                   BIGINT REFERENCES "Category" (`id`),
    `newestConversionRateToDollar` DOUBLE PRECISION NOT NULL
);
-- 
-- CREATE TABLE IF NOT EXISTS "CarRide"
-- (
--     `id`                           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--     `guid`                         VARCHAR          NOT NULL UNIQUE,
--     `currencyId`                   BIGINT REFERENCES "CurrencyEntity" (`id`),
--     `title`                        VARCHAR          NOT NULL,
--     `description`                  VARCHAR          NOT NULL,
--     `distance`                     DOUBLE PRECISION NOT NULL,
--     `fuelConsumption`              DOUBLE PRECISION NOT NULL,
--     `costOfFuelPerLitre`           DOUBLE PRECISION NOT NULL,
--     `numberOfPassengers`           integer          NOT NULL,
--     `commission`                   DOUBLE PRECISION NOT NULL,
--     `categoryId`                   BIGINT REFERENCES "Category" (`id`),
--     `newestConversionRateToDollar` DOUBLE PRECISION NOT NULL,
--     `date`                         datetime         NOT NULL,
-- );
