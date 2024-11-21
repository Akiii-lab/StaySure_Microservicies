DROP DATABASE IF EXISTS stay_sure_database;

CREATE DATABASE stay_sure_database;

\c stay_sure_database;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone BIGINT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    identification_number INT UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    notification_enabled BOOLEAN,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE lesses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone BIGINT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    identification_number INT UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    nit_less BIGINT NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE checkers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone BIGINT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    identification_number INT UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    start_to_checker DATE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE admins (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone BIGINT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    identification_number INT UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    date_to_admin DATE NOT NULL,
    fire_to_admin DATE,
    password VARCHAR(255) NOT NULL
);