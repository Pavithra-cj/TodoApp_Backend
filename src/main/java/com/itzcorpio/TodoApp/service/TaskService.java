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
        return taskRepository.findAllByCompletedFalseOrderByCreatedAtDesc();
    }

    public List<Task> latestTop5Tasks() {
        return taskRepository.findTop5ByCompletedFalseOrderByCreatedAtDesc();
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findAllByCompletedTrueOrderByCreatedAtDesc();
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
            task.setPriority(updatedTask.getPriority());
            task.setDueDate(updatedTask.getDueDate());
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
