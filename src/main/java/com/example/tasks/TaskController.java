package com.example.tasks;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskManager manager;

    public TaskController(TaskManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public List<Task> list() {
        return manager.list();
    }

    @PostMapping
    public Task add(@RequestBody NewTask request) {
        String title = request.title();
        Task resultTask = manager.add(title);
        return resultTask;
    }

    /**
     * The JSON body a client sends to create a task, e.g. {"title": "Learn
     * Docker"}. Kept separate from Task so callers can only supply the title.
     */
    public record NewTask(String title) {
    }
}