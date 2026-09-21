package com.example.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void defaultConstructorUsesTestTitle() {
        assertEquals("test", new Task().getTitle());
    }

    @Test
    void titleConstructorStoresTitle() {
        assertEquals("Download Files", new Task("Download Files").getTitle());
    }

    @Test
    void idConstructorBuildsTitleFromId() {
        assertEquals("Task #101", new Task(101).getTitle());
    }

    @Test
    void newTaskExecutesTask() {
        assertEquals("Executing 'test'", new Task().executeTask());
    }

}