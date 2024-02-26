create database alumnos;

use alumnos;


select * from alumno;

CREATE TABLE alumno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    grado ENUM('DAW', 'DAM', 'ASIR') NOT NULL
);




