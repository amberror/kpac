CREATE DATABASE IF NOT EXISTS kpac;

USE kpac;

CREATE TABLE IF NOT EXISTS kpac (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description VARCHAR(2000),
    creation_date CHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS kpac_set (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS kpac_set_kpac (
    kpac_set_id INT,
    kpac_id INT,
    PRIMARY KEY (kpac_set_id, kpac_id),
    FOREIGN KEY (kpac_set_id) REFERENCES kpac_set(id),
    FOREIGN KEY (kpac_id) REFERENCES kpac(id)
);