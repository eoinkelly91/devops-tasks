// TaskManager.java
package com.example.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task add(String title) {
        Task task = new Task(nextId++, title, false);
        tasks.add(task);
        return task;
    }

    public List<Task> list() {
        return List.copyOf(tasks);
    }

    public void complete(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id() == id) {
                tasks.set(i, tasks.get(i).complete());
                return;
            }
        }
        throw new IllegalArgumentException("No task with id " + id);
    }
}