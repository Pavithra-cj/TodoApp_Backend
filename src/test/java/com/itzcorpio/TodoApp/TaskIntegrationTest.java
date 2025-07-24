package com.itzcorpio.TodoApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itzcorpio.TodoApp.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Task testTask;

    @BeforeEach
    void setup() {
        testTask = new Task();
        testTask.setTitle("Test Task");
        testTask.setDescription("This is a test task description.");
        testTask.setPriority(Task.Priority.Medium);
        testTask.setDueDate(LocalDateTime.now().plusDays(2));
    }

    @Test
    void shouldCreateTask() throws Exception {
        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task description."))
                .andExpect(jsonPath("$.priority").value("Medium"));
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        shouldCreateTask();

        mockMvc.perform(get("/api/v1/tasks/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void shouldGetLatestTop5Tasks() throws Exception {
        for (int i = 0; i < 6; i++) {
            Task task = new Task();
            task.setTitle("Task " + i);
            task.setDescription("Description for task " + i);
            task.setPriority(Task.Priority.Medium);
            task.setDueDate(LocalDateTime.now().plusDays(i));
            mockMvc.perform(post("/api/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(task)));
        }

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void shouldCompleteTask() throws Exception {
        shouldCreateTask();

        mockMvc.perform(put("/api/v1/tasks/complete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void shouldGetCompletedTasks() throws Exception {
        shouldCreateTask();

        mockMvc.perform(put("/api/v1/tasks/complete/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/tasks/completed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].completed", is(true)));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        shouldCreateTask();

        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated description.");
        updatedTask.setPriority(Task.Priority.High);
        updatedTask.setDueDate(LocalDateTime.now().plusDays(3));

        mockMvc.perform(put("/api/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated description."))
                .andExpect(jsonPath("$.priority").value("High"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        shouldCreateTask();

        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/tasks/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
