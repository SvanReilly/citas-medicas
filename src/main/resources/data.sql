DROP TABLE IF EXISTS Data;

USE Data;

CREATE TABLE RESERVATION (
id INT AUTO_INCREMENT PRIMARY KEY,
reservation_date DATE NULL DEFAULT NULL,
name VARCHAR(250) NULL,
status VARCHAR(25) NOT NULL DEFAULT 'FREE'
);

INSERT INTO RESERVATION (fecha, nombre_paciente, estado) VALUES ('2023-11-20 10:00:00', 'Alejandro Ortega', 'Pendiente');
INSERT INTO RESERVATION (fecha, nombre_paciente, estado) VALUES ('2023-11-20 11:00:00', 'Isabel Plaza', 'Confirmada');
