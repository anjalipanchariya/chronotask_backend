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
        List<Task> todaysTasks = taskRepo.findByDate(date);

        LocalDate yesterday = date.minusDays(1);

        List<Task> carryOver = taskRepo.findByDateAndIsCompletedFalse(yesterday);

        for(Task t : carryOver){
            boolean alreadyExists = todaysTasks.stream()
                    .anyMatch(task ->
                            (task.getOriginalTaskId() != null && task.getOriginalTaskId().equals(t.getId())) ||
                                    task.getDescription().equals(t.getDescription()));
            if (!alreadyExists) {
                Task newTask = new Task();
                newTask.setDescription(t.getDescription());
                newTask.setIsCompleted(false);
                newTask.setDate(date);
                newTask.setOriginalTaskId(t.getId());
                todaysTasks.add(taskRepo.save(newTask));
            }
        }
        return todaysTasks;
    }

    public List<Task> saveTasks(List<Task> tasks){
        List<Task> validTasks = tasks.stream()
                .filter(task -> task.getDescription() != null && !task.getDescription().trim().isEmpty())
                .toList();

        if (validTasks.isEmpty()) {
            throw new RuntimeException("No valid tasks to save.");
        }

        return taskRepo.saveAll(validTasks);
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
