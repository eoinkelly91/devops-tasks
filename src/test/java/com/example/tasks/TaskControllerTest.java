package com.example.tasks;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.util.List;

@WebMvcTest(TaskController.class) // loads only the controller and web infrastructure not full app
class TaskControllerTest {

    @Autowired // Spring hands you the ready-made instance.
    private MockMvc mockMvc;

    @MockitoBean
    private TaskManager manager; // builds a fake HTTP request and sends it straight to the controller

    @Test
    void getTasksStartsEmpty() throws Exception {
        when(manager.list()).thenReturn(List.of());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void postTaskAddsAndReturnsIt() throws Exception {
        when(manager.add("Learn DevOps")).thenReturn(new Task("Learn DevOps"));

        mockMvc.perform(post("/tasks")
                .contentType("application/json")
                .content("{\"title\":\"Learn DevOps\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Learn DevOps"));

        verify(manager).add("Learn DevOps");
    }
}