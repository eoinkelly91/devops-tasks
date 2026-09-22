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

    public String executeTask() {
        String taskMessage = "Executing '" + title + "'";
        System.out.println(taskMessage);
        return taskMessage;
    }

    public String getTitle() {
        return title;
    }

}