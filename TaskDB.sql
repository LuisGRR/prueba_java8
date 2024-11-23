-- Crear la base de datos
CREATE DATABASE taskdb;

-- Crear la tabla tasks
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,          -- Identificador único de la tarea
    name VARCHAR(100) NOT NULL,     -- Nombre de la tarea
    description TEXT,               -- Descripción de la tarea
    start_date DATE NOT NULL,       -- Fecha de inicio
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Fecha de última actualización
);


-- Insertar datos de prueba
INSERT INTO tasks (name, description, start_date)
VALUES
('Tarea 1', 'Primera tarea de prueba', '2024-11-01'),
('Tarea 2', 'Segunda tarea de prueba', '2024-11-15'),
('Tarea 3', 'Tercera tarea de prueba con descripción más larga', '2024-12-01');