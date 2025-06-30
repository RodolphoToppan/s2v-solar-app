-- CRIAÇÃO DE TABELAS PARA O BANCO DE DADOS IRRADIATION
CREATE TABLE irradiation.state (
    id_state SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(10) NOT NULL
);

CREATE TABLE irradiation.city (
    id_city SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_state INT NOT NULL,
    CONSTRAINT fk_city_state FOREIGN KEY (id_state) REFERENCES irradiation.state (id_state)
);

CREATE TABLE irradiation.city_irradiation (
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
    CONSTRAINT fk_city_irradiation_city FOREIGN KEY (id_city) REFERENCES irradiation.city (id_city)
);

--- CRIAÇÃO DE DADOS PARA O BANCO DE DADOS PROJECTS
 -- Tabela Client
 CREATE TABLE projects.client (
     id_client SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     id_location INT,
     created_at TIMESTAMP,
     updated_at TIMESTAMP
 );

 -- Tabela Location
 CREATE TABLE projects.location (
     id_location SERIAL PRIMARY KEY,
     state VARCHAR(255),
     city VARCHAR(255),
     address VARCHAR(255),
 );

 -- Tabela MonthGeneration
 CREATE TABLE projects.month_generation (
     id_month_generation SERIAL PRIMARY KEY,
     jan NUMERIC,
     feb NUMERIC,
     mar NUMERIC,
     apr NUMERIC,
     may NUMERIC,
     jun NUMERIC,
     jul NUMERIC,
     aug NUMERIC,
     sep NUMERIC,
     oct NUMERIC,
     nov NUMERIC,
     dec NUMERIC
 );

 -- Tabela Project
 CREATE TABLE projects.project (
     id_project SERIAL PRIMARY KEY,
     id_client INT,
     id_location INT,
     ground BOOLEAN,
     power NUMERIC,
     modules_brand NUMERIC,
     modules_power NUMERIC,
     modules_quantity NUMERIC,
     inverter_brand NUMERIC,
     inverter_power NUMERIC,
     inverter_quantity NUMERIC,
     generation NUMERIC,
     id_month_generation INT,
     created_at TIMESTAMP,
     updated_at TIMESTAMP,
     CONSTRAINT fk_project_client FOREIGN KEY (id_client) REFERENCES projects.client (id_client),
     CONSTRAINT fk_project_location FOREIGN KEY (id_location) REFERENCES projects.location (id_location),
     CONSTRAINT fk_project_month_generation FOREIGN KEY (id_month_generation) REFERENCES projects.month_generation (id_month_generation)
 );

 -- Tabela Consumption
 CREATE TABLE projects.consumption (
     id_consumption SERIAL PRIMARY KEY,
     jan NUMERIC,
     feb NUMERIC,
     mar NUMERIC,
     apr NUMERIC,
     may NUMERIC,
     jun NUMERIC,
     jul NUMERIC,
     aug NUMERIC,
     sep NUMERIC,
     oct NUMERIC,
     nov NUMERIC,
     dec NUMERIC
 );

 -- Tabela Bill
 CREATE TABLE projects.bill (
     id_bill SERIAL PRIMARY KEY,
     uc BIGINT,
     holder VARCHAR(255),
     id_location INT,
     amount NUMERIC,
     tariff NUMERIC,
     average_consumption NUMERIC,
     id_consumption INT,
     id_project INT,
     created_at TIMESTAMP,
     updated_at TIMESTAMP,
     CONSTRAINT fk_bill_location FOREIGN KEY (id_location) REFERENCES projects.location (id_location),
     CONSTRAINT fk_bill_project FOREIGN KEY (id_project) REFERENCES projects.project (id_project),
     CONSTRAINT fk_bill_consumption FOREIGN KEY (id_consumption) REFERENCES projects.consumption (id_consumption)
 );