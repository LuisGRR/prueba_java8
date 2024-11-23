package com.prueba_tecnica.prueba_java8.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Data Transfer Object (DTO) para la tarea.
 * 
 * Esta clase es utilizada para la transferencia de datos entre la capa de presentación
 * y la capa de servicio, facilitando la conversión de los datos entre el modelo y los objetos
 * que se comunican con la interfaz de usuario o APIs.
 * 
 * @see Task
 */
public class TaskDTO {
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name; 

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String name, String description, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
