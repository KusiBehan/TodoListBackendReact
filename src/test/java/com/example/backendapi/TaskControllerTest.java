package com.example.backendapi;

import com.backendapi.BackendApiApplication;
import com.backendapi.api.model.Task;
import com.backendapi.service.TaskService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BackendApiApplication.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private TaskService taskService;

    @Before
    public void setup() {
        org.mockito.MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskService).build();
    }

    @AfterEach
    public void setupafter() {
        org.mockito.MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskService).build();
    }

    @Test
    public void testGetTask() throws Exception {
        Task mockTask = new Task(1, "Mock Task", true);
        Mockito.when(taskService.getTask(1)).thenReturn(mockTask);
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("task").value("Mock Task"))
                .andExpect(jsonPath("status").value(true));
    }

    @Test
    public void getTasks() throws Exception {
        Task mockTaskOne = new Task(1, "mockOne", true);
        Task mockTaskTwo = new Task(2, "mockTwo", false);
        List<Task> taskList = new ArrayList<>(Arrays.asList(mockTaskOne, mockTaskTwo));
        Mockito.when(taskService.getTasks()).thenReturn(taskList);
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"task\":\"mockOne\",\"status\":true},{\"id\":2,\"task\":\"mockTwo\",\"status\":false}]"));
    }

    @Test
    public void createTask() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Task mockTaskInRequestBody = new Task(7, "mockTaskForBody", true);

        Task mockTaskOtherValueForTesting = new Task(66, "testTaskOtherValue", true);
        String mockTaskOtherValueForTestingAsJsonString = mapper.writeValueAsString(mockTaskOtherValueForTesting);
        String mockTaskInRequestBodyAsString = mapper.writeValueAsString(mockTaskInRequestBody);
        Mockito.when(taskService.addTask(Mockito.any(Task.class))).thenReturn(mockTaskOtherValueForTesting);
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(mockTaskInRequestBodyAsString))
                .andExpect(status().isOk())
                .andExpect(content().string(mockTaskOtherValueForTestingAsJsonString));
    }

    @Test
    public void createTaskWithoutBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createTaskFalseParam() throws Exception {
        String FalseTask = "{edewdew}";

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(FalseTask))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void PatchTask() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Task secondTaskTest = new Task(33, "secondTask", true);
        Task taskTest = new Task(55, "TaskChanger", false);
        String secondTaskTestAsString = mapper.writeValueAsString(secondTaskTest);
        String firstTaskTestAsString = mapper.writeValueAsString(taskTest);

        Mockito.when(taskService.SetTodo(Mockito.any(Integer.class), Mockito.any(Boolean.class))).thenReturn(taskTest);
        mockMvc.perform(MockMvcRequestBuilders.patch("/tasks/{id}", 4).content(secondTaskTestAsString))
                .andExpect(content().string(firstTaskTestAsString));
    }

    @Test
    public void PatchTaskFalseParam() throws Exception {
        String FalseTask = "{edewdew}";
        mockMvc.perform(MockMvcRequestBuilders.patch("/tasks", 2).contentType(MediaType.APPLICATION_JSON).content(FalseTask))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void PatchWithoutRequestBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/tasks", 2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void DeleteTask() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Task deletedTask = new Task(2, "taskDeleted", false);
        String deletedTaskAsString = mapper.writeValueAsString(deletedTask);
        Mockito.when(taskService.deleteTask(3)).thenReturn(deletedTask);

        mockMvc.perform(MockMvcRequestBuilders.delete("/task").param("id", "3"))
                .andExpect(content().string(deletedTaskAsString));
    }

    @Test
    public void deleteWithoutParam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/task"))
                .andExpect(status().is4xxClientError());
    }
}
