package com.itzcorpio.TodoApp.repository;

import com.itzcorpio.TodoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //Get all tasks
    List<Task> findAllByCompletedFalseOrderByCreatedAtDesc();

    //Get the top 5 tasks
    List<Task> findTop5ByCompletedFalseOrderByCreatedAtDesc();

    //Get all completed tasks
    List<Task> findAllByCompletedTrueOrderByCreatedAtDesc();
}
