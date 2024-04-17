package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @PutMapping("/updateTask/{id}")
    public void updateTask(@PathVariable Long id,@RequestBody Task task){
        task.setId(id);
        taskService.updateTask(task);
    }

    @PutMapping("/updateTaskStatus/{id}")
    public ResponseEntity updateTaskStatus(@PathVariable Long id,@RequestBody Task task){
        task.setId(id);
        if(task.getTitle().isEmpty()){
            task.setTitle(taskService.getTaskById(id).getTitle());
        }
        return taskService.updateTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }
}
