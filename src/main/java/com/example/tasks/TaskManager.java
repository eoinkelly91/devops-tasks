
package com.example.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public Task add(String title) {
        Task task = new Task(title);
        tasks.add(task);
        return task;
    }

    // Overloaded add method for int
    public Task add(int id) {
        Task task = new Task(id);
        tasks.add(task);
        return task;
    }

    public List<Task> list() {
        return List.copyOf(tasks);
    }
}