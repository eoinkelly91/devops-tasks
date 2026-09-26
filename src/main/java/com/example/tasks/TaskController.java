package com.example.tasks;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Task> list() {
        return repository.findAll();
    }

    @PostMapping
    public Task add(@RequestBody NewTask request) {
        Task task = new Task(request.title());
        return repository.save(task);
    }

    public record NewTask(String title) {
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> delete(@PathVariable String title) {
        if (!repository.existsByTitle(title)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteAllByTitle(title);
        return ResponseEntity.noContent().build();
    }
}