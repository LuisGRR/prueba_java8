package com.prueba_tecnica.prueba_java8.mapper;

import org.springframework.stereotype.Service;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.model.Task;

/**
 * Mapper para convertir un {@link TaskDTO} en una entidad {@link Task}.
 * 
 * Esta clase es responsable de transformar los DTOs de tarea recibidos en la
 * capa de solicitud
 * en las entidades que serán almacenadas o procesadas por la lógica de negocio.
 */
@Service
public class RequestTaskMapper {

    /**
     * Convierte un {@link TaskDTO} en una entidad {@link Task}.
     * 
     * @param taskDTO El DTO de la tarea que se va a convertir.
     * @return Un objeto {@link Task} con los datos de la tarea, o null si el DTO es
     *         null.
     */
    public Task taskDTOToTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStart_date(taskDTO.getStartDate());
        return task;
    }

    /**
     * Convierte un {@link TaskDTO} en una entidad {@link Task} y asigna un ID a la
     * tarea.
     * 
     * Este método se utiliza cuando se quiere asignar un ID específico a la tarea
     * durante su conversión.
     * 
     * @param taskDTO El DTO de la tarea que se va a convertir.
     * @param id      El ID que se asignará a la tarea.
     * @return Un objeto {@link Task} con los datos de la tarea y el ID asignado, o
     *         null si el DTO es null.
     */
    public Task taskDTOToTask(TaskDTO taskDTO, Long id) {
        if (taskDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStart_date(taskDTO.getStartDate());
        return task;
    }
}
