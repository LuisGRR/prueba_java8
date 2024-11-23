package com.prueba_tecnica.prueba_java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.mapper.ResponseTaskMapper;
import com.prueba_tecnica.prueba_java8.model.Task;

public class ResponseTaskMapperTest {
    private ResponseTaskMapper responseTaskMapper;

    @BeforeEach
    public void setUp() {
        responseTaskMapper = new ResponseTaskMapper();
    }

    @Test
    public void testTaskToTaskResponseDTO() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDescription("Descripcion de la Task 1");
        task.setStart_date(LocalDate.of(2024, 11, 22));

        TaskDTO taskDTO = responseTaskMapper.taskToTaskResponseDTO(task);

        assertNotNull(taskDTO);

        assertEquals(1L, taskDTO.getId());
        assertEquals("Task 1", taskDTO.getName());
        assertEquals("Descripcion de la Task 1", taskDTO.getDescription());
        assertEquals(LocalDate.of(2024, 11, 22), taskDTO.getStartDate());
    }

    @Test
    public void testTaskToTaskResponseDTO_withNullTask() {
        TaskDTO taskDTO = responseTaskMapper.taskToTaskResponseDTO(null);

        assertNull(taskDTO);
    }
}
