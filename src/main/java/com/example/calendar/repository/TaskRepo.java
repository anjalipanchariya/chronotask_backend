package com.example.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.calendar.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    //usually we do not write anything inside this if we want custom queries we can write here

    List<Task> findByDate(LocalDate date);
}
