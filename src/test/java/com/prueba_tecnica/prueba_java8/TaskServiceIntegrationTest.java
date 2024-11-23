package com.prueba_tecnica.prueba_java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.exception.TaskNotFoundException;
import com.prueba_tecnica.prueba_java8.mapper.TaskMapper;
import com.prueba_tecnica.prueba_java8.model.Task;
import com.prueba_tecnica.prueba_java8.service.TaskService;

@SpringBootTest
@Transactional
public class TaskServiceIntegrationTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper; 

    private TaskDTO taskDTO;

    @BeforeEach
    public void setUp() {
        taskDTO = new TaskDTO();
        taskDTO.setName("Task 1");
        taskDTO.setDescription("Descripcion de la Task");
        taskDTO.setStartDate(LocalDate.now()); 
    }

    @Test
    public void testInsertTask_ShouldSaveInDatabase() {
        taskService.insertTask(taskDTO);

        Task savedTask = taskMapper.getAllTasks().stream()
                .filter(task -> task.getName().equals("Task 1"))
                .findFirst()
                .orElse(null);

        assertNotNull(savedTask);
        assertEquals("Task 1", savedTask.getName());
        assertEquals("Descripcion de la Task", savedTask.getDescription());
    }

    @Test
    public void testUpdateTask_ShouldUpdateInDatabase() {
        Task task = new Task();
        task.setName("Initial Task");
        task.setDescription("Descripcion de la Task");
        task.setStart_date(LocalDate.now());

        taskMapper.insertTask(task);

        assertNotNull(task.getId());

        Task insertedTask = taskMapper.getTaskById(task.getId());
        assertNotNull(insertedTask);

        taskDTO.setName("Actualizar Task");
        taskDTO.setDescription("Actualizar Descripcion de la Task");
        taskService.updateTask(insertedTask.getId(), taskDTO);

        Task updatedTask = taskMapper.getTaskById(insertedTask.getId());

        assertNotNull(updatedTask);
        assertEquals("Actualizar Task", updatedTask.getName());
        assertEquals("Actualizar Descripcion de la Task", updatedTask.getDescription());
    }

    @Test
    public void testDeleteTask_ShouldRemoveFromDatabase() {
        Task task = new Task();
        task.setName("Eliminar la Task");
        task.setDescription("Descripcion de la Task");
        task.setStart_date(LocalDate.now());

        taskMapper.insertTask(task);
        taskService.deleteTask(task.getId());

        Task deletedTask = taskMapper.getTaskById(task.getId());

        assertNull(deletedTask);
    }

    @Test
    public void testInsertTask_WithNullFields_ShouldThrowException() {
        TaskDTO taskDTOWithNullFields = new TaskDTO();
        taskDTOWithNullFields.setName(null); 
        taskDTOWithNullFields.setDescription(""); 
        taskDTOWithNullFields.setStartDate(LocalDate.now());

        try {
            taskService.insertTask(taskDTOWithNullFields);
        } catch (DataIntegrityViolationException e) {
            assertNotNull(e);
            return; 
        }

        fail("DataIntegrityViolationException esperada debido a un nombre nulo.");
    }

    @Test
    public void testDeleteTask_TaskNotFound_ShouldThrowException() {
        assertThrows(TaskNotFoundException.class, () -> {
            taskService.deleteTask(99999L);
        });
    }

    @Test
    public void testUpdateTask_TaskNotFound_ShouldThrowException() {
        TaskDTO nonExistingTaskDTO = new TaskDTO();
        nonExistingTaskDTO.setName("No existe la Task");
        nonExistingTaskDTO.setDescription("Descripcion de la Task");
        nonExistingTaskDTO.setStartDate(LocalDate.now());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.updateTask(99999L, nonExistingTaskDTO);
        });
    }

}
