package com.example.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskManagerTest {

    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test
    void newManagerHasNoTasks() {
        assertTrue(manager.list().isEmpty());
    }

    @Test
    void addWithTitleAddsTask() {
        Task task = manager.add("Write tests");

        assertEquals("Write tests", task.getTitle());
        assertEquals(1, manager.list().size());
    }

    @Test
    void addWithIdAddsTask() {
        manager.add(101);

        assertEquals("Task #101", manager.list().get(0).getTitle());
    }

    @Test
    void listPreservesInsertionOrder() {
        manager.add("first");
        manager.add("second");

        assertEquals("first", manager.list().get(0).getTitle());
        assertEquals("second", manager.list().get(1).getTitle());
    }

    @Test
    void returnedListCannotBeModified() {
        manager.add("first");
        List<Task> tasks = manager.list();

        assertThrows(UnsupportedOperationException.class, () -> tasks.add(new Task()));
    }

    @Test
    void deleteRemovesMatchingTask() {
        manager.add("Write tests");

        boolean removed = manager.delete("Write tests");

        assertTrue(removed);
        assertTrue(manager.list().isEmpty());
    }

    @Test
    void deleteReturnsFalseWhenNotFound() {
        boolean removed = manager.delete("Nonexistent");

        assertFalse(removed);
    }
}