package com.example.backendapi;

import com.backendapi.BackendApiApplication;
import org.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


@SpringBootTest(classes = BackendApiApplication.class)
public class HttpRequestTest {

/*
    private final TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void statusCodeOK() {
        int status = 200;
        var expected = restTemplate.getForEntity("http://localhost:8080/tasks", String.class).getStatusCode();
        assertEquals(status, expected.value());
    }

    // Check Status 415 post with unsuported media type (formdata && !=JSON)
    @Test
    public void statusFailureWithoutTaskRoot() {
        int status = 404;
        var actual = restTemplate.getForEntity("http://localhost:8080/", String.class).getStatusCode();
        assertEquals(status, actual.value());
    }

    @Test
    public void Arraylengthatstart() throws JSONException {
        String response = restTemplate.getForObject("http://localhost:8080/tasks", String.class);
        JSONArray jsonArray = new JSONArray(response);
        assertEquals(5, jsonArray.length());
    }
    */

}
