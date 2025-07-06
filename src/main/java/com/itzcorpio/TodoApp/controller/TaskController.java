package com.itzcorpio.TodoApp.controller;

import com.itzcorpio.TodoApp.model.Task;
import com.itzcorpio.TodoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Task>> getRecentTasks() {
        List<Task> recentTasks = taskService.getRecentTasks();
        return ResponseEntity.ok(recentTasks);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> completeTask(@PathVariable Long id) {
        return taskService.completeTask(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
