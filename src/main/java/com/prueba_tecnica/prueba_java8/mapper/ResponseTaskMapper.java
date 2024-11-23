package com.prueba_tecnica.prueba_java8.mapper;

import org.springframework.stereotype.Service;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.model.Task;

/**
 * Mapper para convertir una entidad {@link Task} en un DTO de respuesta
 * {@link TaskDTO}.
 * 
 * Este servicio es responsable de transformar las entidades de tarea en un
 * formato adecuado
 * para la presentación en la capa de respuesta de la aplicación.
 */
@Service
public class ResponseTaskMapper {

    /**
     * Convierte una entidad {@link Task} en un {@link TaskDTO} para la respuesta.
     * 
     * @param task La entidad {@link Task} que se va a convertir.
     * @return Un objeto {@link TaskDTO} con los datos de la tarea, o null si la
     *         tarea es null.
     */
    public TaskDTO taskToTaskResponseDTO(Task task) {
        if (task == null) {
            return null;
        }
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStartDate(task.getStart_date());
        return dto;
    }
}
