package com.backendapi.api.controller;

import com.backendapi.api.model.Task;
import com.backendapi.service.TaskService;
import com.fasterxml.jackson.core.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TaskController {

    private final TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



    @GetMapping("/tasks/{id}")
    public Task getTaskbyid(@PathVariable Integer id) {
        return taskService.getTask(id);
    }


    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/tasks")
    public Task create(@RequestBody Task newTask) {
        return taskService.addTask(newTask);
    }

    @PatchMapping("/tasks/{id}")
    public Task changeStatusTask(@PathVariable Integer id, @RequestBody String content) {
        JSONObject jsonObject = new JSONObject(content);
        boolean status = jsonObject.getBoolean("status");
        return taskService.SetTodo(id, status);
    }

    @DeleteMapping("task")
    public Task delete(@RequestParam Integer id) {
        return taskService.deleteTask(id);
    }
}











