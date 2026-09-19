// Main.java
package com.example.tasks;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.add("Learn Git");
        manager.add("Learn Maven");
        manager.complete(1);
        manager.list().forEach(System.out::println);
    }
}