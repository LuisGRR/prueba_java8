package com.prueba_tecnica.prueba_java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.mapper.RequestTaskMapper;
import com.prueba_tecnica.prueba_java8.model.Task;

public class RequestTaskMapperTest {

    private RequestTaskMapper requestTaskMapper;

    @BeforeEach
    public void setUp() {
        requestTaskMapper = new RequestTaskMapper();
    }

    @Test
    public void testTaskDTOToTask() {
        TaskDTO taskDTO = new TaskDTO(1L, "Task 1", "Descripcion de la Task 1", LocalDate.of(2024, 11, 22));

        Task task = requestTaskMapper.taskDTOToTask(taskDTO);

        assertNotNull(task);
        
        assertEquals("Task 1", task.getName());
        assertEquals("Descripcion de la Task 1", task.getDescription());
        assertEquals(LocalDate.of(2024, 11, 22), task.getStart_date());
        assertNull(task.getId());
    }

    @Test
    public void testTaskDTOToTask_withNullDTO() {
        Task task = requestTaskMapper.taskDTOToTask(null);
        assertNull(task);
    }

    @Test
    public void testTaskDTOToTaskWithId() {
        TaskDTO taskDTO = new TaskDTO(2L, "Task 2", "Descripcion de la Task 2", LocalDate.of(2024, 11, 23));

        Task task = requestTaskMapper.taskDTOToTask(taskDTO, 123L);

        assertNotNull(task);

        assertEquals("Task 2", task.getName());
        assertEquals("Descripcion de la Task 2", task.getDescription());
        assertEquals(LocalDate.of(2024, 11, 23), task.getStart_date());
        assertEquals(123L, task.getId());
    }

    @Test
    public void testTaskDTOToTaskWithId_withNullDTO() {
        Task task = requestTaskMapper.taskDTOToTask(null, 123L);

        assertNull(task);
    }
}
