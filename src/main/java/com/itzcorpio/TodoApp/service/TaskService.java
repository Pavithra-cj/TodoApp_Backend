package com.itzcorpio.TodoApp.service;

import com.itzcorpio.TodoApp.model.Task;
import com.itzcorpio.TodoApp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Task> getRecentTasks() {
        return taskRepository.findTop5ByCompletedFalseOrderByCreatedAtDesc();
    }

    public Optional<Task> completeTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            task.setCompleted(true);
            return taskRepository.save(task);
        });
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            return taskRepository.save(task);
        });
    }

    public Boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
