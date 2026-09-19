// Task.java
package com.example.tasks;

public record Task(int id, String title, boolean done) {
    public Task complete() {
        return new Task(id, title, true);
    }
}