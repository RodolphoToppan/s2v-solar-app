CREATE TABLE state (
    id_state SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(10) NOT NULL
);

CREATE TABLE city (
    id_city SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_state INT NOT NULL,
    CONSTRAINT fk_city_state FOREIGN KEY (id_state) REFERENCES state (id_state)
);

CREATE TABLE city_irradiation (
    id_city_irradiation SERIAL PRIMARY KEY,
    annual_irradiation DOUBLE PRECISION,
    jan DOUBLE PRECISION,
    feb DOUBLE PRECISION,
    mar DOUBLE PRECISION,
    apr DOUBLE PRECISION,
    may DOUBLE PRECISION,
    jun DOUBLE PRECISION,
    jul DOUBLE PRECISION,
    aug DOUBLE PRECISION,
    sep DOUBLE PRECISION,
    oct DOUBLE PRECISION,
    nov DOUBLE PRECISION,
    dec DOUBLE PRECISION,
    id_city INT NOT NULL,
    CONSTRAINT fk_city_irradiation_city FOREIGN KEY (id_city) REFERENCES city (id_city)
);