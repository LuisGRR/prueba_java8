package com.prueba_tecnica.prueba_java8.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba_tecnica.prueba_java8.model.Task;

/**
 * Mapper para realizar operaciones CRUD sobre las tareas.
 * 
 * Esta interfaz interactúa con la base de datos para recuperar, insertar,
 * actualizar y eliminar tareas.
 */
@Mapper
public interface TaskMapper {

    /**
     * Obtiene todas las tareas almacenadas en la base de datos.
     * 
     * @return Una lista de todas las tareas.
     */
    List<Task> getAllTasks();

    /**
     * Obtiene una tarea específica utilizando su ID.
     * 
     * @param id El ID de la tarea a obtener.
     */
    Task getTaskById(Long id);

    /**
     * Inserta una nueva tarea en la base de datos.
     * 
     * @param task La tarea que se desea insertar.
     */
    void insertTask(Task task);

    /**
     * Actualiza una tarea existente en la base de datos.
     * 
     * @param task La tarea que se desea actualizar.
     */
    void updateTask(Task task);

    /**
     * Elimina una tarea de la base de datos usando su ID.
     * 
     * @param id El ID de la tarea a eliminar.
     */
    void deleteTask(Long id);
}
