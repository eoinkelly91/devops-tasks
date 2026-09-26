package com.example.tasks;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskRepository repository;

    @Test
    void getTasksStartsEmpty() throws Exception {
        when(repository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void postTaskAddsAndReturnsIt() throws Exception {
        when(repository.save(org.mockito.ArgumentMatchers.any(Task.class)))
                .thenReturn(new Task("Learn DevOps"));

        mockMvc.perform(post("/tasks")
                .contentType("application/json")
                .content("{\"title\":\"Learn DevOps\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Learn DevOps"));
    }

    @Test
    void deleteExistingTaskReturnsNoContent() throws Exception {
        when(repository.existsByTitle("LearnDocker")).thenReturn(true);

        mockMvc.perform(delete("/tasks/LearnDocker"))
                .andExpect(status().isNoContent());

        verify(repository).deleteAllByTitle("LearnDocker");
    }

    @Test
    void deleteMissingTaskReturnsNotFound() throws Exception {
        when(repository.existsByTitle("Nonexistent")).thenReturn(false);

        mockMvc.perform(delete("/tasks/Nonexistent"))
                .andExpect(status().isNotFound());
    }
}