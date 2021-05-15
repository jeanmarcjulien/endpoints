package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.is;


@WebMvcTest(PagesController.class)
public class PagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHello() throws Exception {

        RequestBuilder request = get("/hello");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void testQueryStrings() throws Exception{

        RequestBuilder request = get("/greeting?first=jean&last=julien");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("hello jean julien sending query string data"));

    }

    @Test
    public void testPathVariables() throws Exception{

        RequestBuilder request = get("/greeting/mike/gaffney");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("hello mike gaffney sending path variable data"));

    }

    @Test
    public void testFormData() throws Exception{

        RequestBuilder request = post("/greeting")
                .param("first", "jean-marc")
                .param("last", "julien");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("hello jean-marc julien sending form data"));

    }

    //Write a test that covers an endpoint that returns JSON

    @Test
    public void testJsonObject() throws Exception {

        RequestBuilder request = get("/json-object");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("jean-marc")))
                .andExpect(jsonPath("$.lastName", is("julien")));
    }

    //test json response for json array

    @Test
    public void testJsonArray() throws Exception {

        RequestBuilder request = get("/json-array");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("jean-marc")))
                .andExpect(jsonPath("$[1].lastName", is("gaffney")));
    }

    //Write a test that sends JSON data to an endpoint

    @Test
    public void testJsonObjectParams() throws Exception {
        RequestBuilder request = post("/json-data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"jean-marc\", \"lastName\": \"julien\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("hello jean-marc julien"));
    }
}