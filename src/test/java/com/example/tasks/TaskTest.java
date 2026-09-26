package com.example.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void titleConstructorStoresTitle() {
        Task task = new Task("Learn Docker");

        assertEquals("Learn Docker", task.getTitle());
    }

    @Test
    void idIsNullBeforePersisting() {
        Task task = new Task("Learn Docker");

        assertNull(task.getId());
    }
}