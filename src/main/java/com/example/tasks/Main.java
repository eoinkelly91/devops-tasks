
package com.example.tasks;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.add("Download Files");
        manager.add(101);
        manager.list().forEach(Task::executeTask);
    }
}