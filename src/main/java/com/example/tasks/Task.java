// Task.java
package com.example.tasks;

public class Task {

    private String title;

    public Task() {
        this.title = "test";
    }

    public Task(String title) {
        this.title = title;
    }

    public Task(int id) {
        this.title = "Task #" + id;
    }

    public void executeTask() {
        System.out.println("Executing '" + title + "'");
    }

    public String getTitle() {
        return title;
    }

}