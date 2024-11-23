package com.prueba_tecnica.prueba_java8.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.exception.TaskNotFoundException;
import com.prueba_tecnica.prueba_java8.mapper.RequestTaskMapper;
import com.prueba_tecnica.prueba_java8.mapper.ResponseTaskMapper;
import com.prueba_tecnica.prueba_java8.mapper.TaskMapper;
import com.prueba_tecnica.prueba_java8.model.Task;

/**
 * Servicio encargado de gestionar las tareas.
 * Proporciona funcionalidades para crear, actualizar, obtener y eliminar
 * tareas.
 */
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ResponseTaskMapper responseTaskMapper;

    @Autowired
    private RequestTaskMapper requestTaskMapper;

    /**
     * Obtiene todas las tareas.
     * 
     * @return Una lista de todas las tareas.
     */
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskMapper.getAllTasks();

        return tasks.stream()
                .map(responseTaskMapper::taskToTaskResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una tarea por su ID.
     * 
     * @param id El ID de la tarea a obtener.
     * @return La tarea correspondiente al ID.
     */
    public TaskDTO geTaskById(Long id) {
        Task task = taskMapper.getTaskById(id);
        if (task == null) {
            return null;
        }

        return responseTaskMapper.taskToTaskResponseDTO(task);
    }

    /**
     * Guarda una tarea.
     * 
     * @param task La tarea a guardar.
     */
    public void insertTask(TaskDTO taskDTO) {
        Task task = requestTaskMapper.taskDTOToTask(taskDTO);
        taskMapper.insertTask(task);
    }

    /**
     * Actualiza una tarea existente en la base de datos.
     * 
     * @param task La tarea a actualizar.
     */
    public void updateTask(Long id, TaskDTO taskDto) {
        Task existingTask = taskMapper.getTaskById(id);
        if (existingTask == null) {
            throw new TaskNotFoundException("Task does not exist");
        }
        Task updateTask = requestTaskMapper.taskDTOToTask(taskDto, id);
        taskMapper.updateTask(updateTask);
    }

    /**
     * Elimina una tarea de la base de datos.
     * 
     * @param id El ID de la tarea a eliminar.
     */
    public void deleteTask(Long id) {
        Task existingTask = taskMapper.getTaskById(id);
        if (existingTask == null) {
            throw new TaskNotFoundException("Task does not exist");
        }
        taskMapper.deleteTask(id);
    }

}
