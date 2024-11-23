package com.prueba_tecnica.prueba_java8.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba_tecnica.prueba_java8.DTO.TaskDTO;
import com.prueba_tecnica.prueba_java8.service.TaskService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String listTasks(Model model) {
        List<TaskDTO> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks.isEmpty() ? Collections.emptyList() : tasks);
        return "task-list";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new TaskDTO());
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute TaskDTO taskDTO) {
        taskService.insertTask(taskDTO);
        return "redirect:/tasks/list/";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable("id") Long id, Model model) {
        TaskDTO existingTask = taskService.geTaskById(id);
        if (existingTask == null) {
            model.addAttribute("error", "Task not found");
            return "redirect:/tasks/list";
        }
        model.addAttribute("task", existingTask);
        return "task-form";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id, @Valid @ModelAttribute TaskDTO task, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            return "task-form";
        }
        try {
            taskService.updateTask(id, task);
        } catch (Exception e) {
            model.addAttribute("error", "Error updating task ");
            return "task-form";
        }
        return "redirect:/tasks/list/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,Model model) {
        try {
            taskService.deleteTask(id);
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting task: " + e.getMessage());
            return "redirect:/tasks/list";
        }
        return "redirect:/tasks/list/"; 
    }

}
