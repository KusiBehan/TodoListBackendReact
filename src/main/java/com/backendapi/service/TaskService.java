package com.backendapi.service;

import com.backendapi.api.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private List<Task> taskList;

    public TaskService() {
        taskList = new ArrayList<>();

        Task task = new Task(1, "First Task", true);
        Task task1 = new Task(2, "Second Task", true);
        Task task2 = new Task(3, "Third Task", true);
        Task task3 = new Task(4, "Fourth Task", true);
        Task task4 = new Task(5, "Fifth Task", true);

        taskList.addAll(Arrays.asList(task, task1, task2, task3, task4));
    }

    public Task getTask(Integer id) {
        for (Task task : taskList) {
            if (id == task.getId()) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public Task addTask(Task newTask) {
        taskList.add(newTask);
        return newTask;
    }

    public Task getTask(Task taskToFind) {
        for (Task task : taskList) {
            if (taskToFind == task) {
                return taskToFind;
            }
        }
        return null;
    }

    public Task SetTodo(Integer id, Boolean status) {
        Task changeTaskStatus = getTask(id);
        changeTaskStatus.setStatus(status);
        return changeTaskStatus;
    }

    public Task deleteTask(Integer id) {
        Task taskToDelete = getTask(id);
        taskList.removeIf(task -> id == task.getId());
        return taskToDelete;
    }
}