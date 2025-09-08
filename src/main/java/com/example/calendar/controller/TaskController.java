package com.example.calendar.controller;

import com.example.calendar.model.Task;
import com.example.calendar.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "https://chronotask-calendar.netlify.app")
public class TaskController {

    @Autowired
    private TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{date}")
    public List<Task> getTasksByDate(@PathVariable String date){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date);
        return taskService.getAllTasks(localDate);

    }

    @PostMapping()
    public List<Task> createTask(@RequestBody List<Task> tasks){
        return taskService.saveTasks(tasks);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/status")
    public void updateTask(@PathVariable Long id, @RequestParam boolean isCompleted){
        taskService.updateTaskStatus(id, isCompleted);
    }
}
