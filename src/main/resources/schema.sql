DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS student
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name    VARCHAR(50)  NOT NULL,
    last_name     VARCHAR(50)  NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    date_of_birth DATE
);

