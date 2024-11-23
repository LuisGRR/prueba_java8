package com.prueba_tecnica.prueba_java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.exception.TaskNotFoundException;
import com.prueba_tecnica.prueba_java8.mapper.RequestTaskMapper;
import com.prueba_tecnica.prueba_java8.mapper.ResponseTaskMapper;
import com.prueba_tecnica.prueba_java8.mapper.TaskMapper;
import com.prueba_tecnica.prueba_java8.model.Task;
import com.prueba_tecnica.prueba_java8.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
   @Mock
    private TaskMapper taskMapper; 

    @Mock
    private ResponseTaskMapper responseTaskMapper; 

    @Mock
    private RequestTaskMapper requestTaskMapper; 

    @InjectMocks
    private TaskService taskService; 

    private Task task;
    private TaskDTO taskDTO;

     @BeforeEach
    public void setUp() {
        task = new Task();
        taskDTO = new TaskDTO();
        taskDTO.setName("Task 1");
        taskDTO.setDescription("Descripcion");
    }

    @Test
    public void testGetAllTasks() {
        when(taskMapper.getAllTasks()).thenReturn(Arrays.asList(task));
        when(responseTaskMapper.taskToTaskResponseDTO(any(Task.class))).thenReturn(taskDTO);

        List<TaskDTO> tasks = taskService.getAllTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
        verify(taskMapper, times(1)).getAllTasks(); 
    }

    @Test
    public void testGetTaskById() {
        when(taskMapper.getTaskById(1L)).thenReturn(task);
        when(responseTaskMapper.taskToTaskResponseDTO(any(Task.class))).thenReturn(taskDTO);

        TaskDTO result = taskService.geTaskById(1L);

        assertNotNull(result);
        assertEquals("Task 1", result.getName());
        verify(taskMapper, times(1)).getTaskById(1L);
    }

    @Test
    public void testGetTaskById_NotFound() {
        when(taskMapper.getTaskById(1L)).thenReturn(null);

        TaskDTO result = taskService.geTaskById(1L);

        assertNull(result);
    }

    @Test
    public void testInsertTask() {
        when(requestTaskMapper.taskDTOToTask(any(TaskDTO.class))).thenReturn(task);

        taskService.insertTask(taskDTO);

        verify(taskMapper, times(1)).insertTask(any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        when(taskMapper.getTaskById(1L)).thenReturn(task);
        when(requestTaskMapper.taskDTOToTask(any(TaskDTO.class), eq(1L))).thenReturn(task);

        taskService.updateTask(1L, taskDTO);

        verify(taskMapper, times(1)).updateTask(any(Task.class));
    }

    @Test
    public void testUpdateTask_TaskNotFound() {
        when(taskMapper.getTaskById(1L)).thenReturn(null);
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(1L, taskDTO));
    }

    @Test
    public void testDeleteTask() {
        when(taskMapper.getTaskById(1L)).thenReturn(task);
        taskService.deleteTask(1L);
        verify(taskMapper, times(1)).deleteTask(1L);
    }

    @Test
    public void testDeleteTask_TaskNotFound() {
        when(taskMapper.getTaskById(1L)).thenReturn(null);
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1L));
    }
}
