package com.example.calendar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

//    @JsonProperty("isCompleted")
    private boolean isCompleted;

    private LocalDate date;

    private Long originalTaskId;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOriginalTaskId(Long originalTaskId) {
        this.originalTaskId = originalTaskId;
    }

    public Long getOriginalTaskId() {
        return originalTaskId;
    }
}
