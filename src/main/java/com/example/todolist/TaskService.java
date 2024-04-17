package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public ResponseEntity saveTask(Task task) {
        taskRepository.save(task);
        return new ResponseEntity("New Task Saved", HttpStatusCode.valueOf(200));
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public ResponseEntity updateTask(Task task) {
        taskRepository.save(task);

        return new ResponseEntity("Task Updated", HttpStatusCode.valueOf(200));
    }
}
