package com.itzcorpio.TodoApp.model;

import jakarta.annotation.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed = false;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.Medium;

    private LocalDateTime dueDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public enum Priority {
        Low,
        Medium,
        High
    }
}
