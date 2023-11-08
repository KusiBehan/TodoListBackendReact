package com.example.backendapi;

import com.backendapi.api.model.Task;
//import org.junit.Before;
//import org.junit.Test;
import com.backendapi.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskService taskService;

    @Before
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    public void testGetTask() {
        Task task = taskService.getTask(1);
        assertNotNull(task);
        assertEquals(1, task.getId());
    }

    @Test
    public void testGetTaskNonExistent() {
        Task task = taskService.getTask(100); // Assuming task with ID 100 doesn't exist
        assertNull(task);
    }

    @Test
    public void testGetTasks() {
        List<Task> tasks = taskService.getTasks();
        assertNotNull(tasks);
        assertEquals(5, tasks.size());
    }

    @Test
    public void testAddTask() {
        Task newTask = new Task(6, "Sixth Task", true);
        Task addedTask = taskService.addTask(newTask);
        assertNotNull(addedTask);
        assertEquals(newTask, addedTask);
    }

    @Test
    public void testSetTodo() {
        Task task = taskService.getTask(1);
        Task updatedTask = taskService.SetTodo(1, false);
        assertNotNull(updatedTask);
        assertEquals(1, updatedTask.getId());
        assertFalse(updatedTask.getStatus());
    }

    @Test
    public void testDeleteTask() {
        Task taskToDelete = taskService.getTask(2);
        Task deletedTask = taskService.deleteTask(2);
        assertNull(taskService.getTask(2)); // Task should be deleted
        assertNotNull(deletedTask);
        assertEquals(taskToDelete, deletedTask);
    }

    // You can add more test cases as needed to cover other scenarios.
}
