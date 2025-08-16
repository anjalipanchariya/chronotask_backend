package com.example.calendar.service;

import com.example.calendar.model.Task;
import com.example.calendar.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> getAllTasks(LocalDate date){
        return taskRepo.findByDate(date);
    }

    public List<Task> saveTasks(List<Task> tasks){
        return taskRepo.saveAll(tasks);
    }

    public void deleteTask(Long id){
        if (!taskRepo.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepo.deleteById(id);
    }

    public Task updateTaskStatus(Long id, boolean isCompleted) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setIsCompleted(isCompleted);
        return taskRepo.save(task);
    }
}
