package com.example.tasks;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public Task add(String title) {
        Task task = new Task(title);
        tasks.add(task);
        return task;
    }

    public Task add(int id) {
        Task task = new Task(id);
        tasks.add(task);
        return task;
    }

    public List<Task> list() {
        return List.copyOf(tasks);
    }
}