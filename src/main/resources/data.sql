DROP SCHEMA IF EXISTS datos;
CREATE DATABASE datos;

USE datos;
CREATE TABLE RESERVAS (
id INT AUTO_INCREMENT PRIMARY KEY,
fecha TIMESTAMP NULL DEFAULT NULL,
nombre_paciente VARCHAR(250) NULL,
estado VARCHAR(25) NOT NULL DEFAULT 'FREE'
);

INSERT INTO RESERVAS (fecha, nombre_paciente, estado) VALUES ('2023-11-20 10:00:00', 'Alejandro Ortega', 'Pendiente');
INSERT INTO RESERVAS (fecha, nombre_paciente, estado) VALUES ('2023-11-20 11:00:00', 'Isabel Plaza', 'Confirmada');


SELECT *  FROM RESERVAS;