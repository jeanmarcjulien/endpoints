package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class PagesController {

    String x=0;

    @GetMapping("/get_x")
    public String getX(){
        return x;
    }

    @PostMapping("/set_x")
    public String setX(@RequestParam String x){
        x=x;
    }

    @GetMapping("/greeting")
    public String getQueryStrings(@RequestParam String first, @RequestParam String last){

        return "hello " + first + " " + last + " sending query string data version 3";
    }

    @GetMapping("/greeting/{first}/{last}")
    public String getPathVariables(@PathVariable String first, @PathVariable String last){

        return "hello " + first + " " + last + " sending path variable data";
    }

    @PostMapping("/greeting")
    //@RequestMapping(value="/greeting", method = RequestMethod.POST)
    public String getFormData(@RequestParam String first, @RequestParam String last){
        return "hello " + first + " " + last + " sending form data";
    }

    //Render a JSON object in a response

    public static class Person {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @GetMapping("/json-object")
    public Person getJsonObject() {
        Person person = new Person();
        person.firstName = "jean-marc";
        person.lastName = "julien";
        return person;
    }

    //Render a list of JSON objects in a response

    @GetMapping("/json-array")
    public List<Person> getJsonArray() {
        Person person1 = new Person();
        person1.firstName = "jean-marc";
        person1.lastName = "julien";

        Person person2 = new Person();
        person2.firstName = "mike";
        person2.lastName = "gaffney";

        return Arrays.asList(person1, person2);
    }

    //Access JSON Request Data

    @PostMapping("/json-data")
    public String getJSONData(@RequestBody Person person) {
        return "hello " + person.firstName + " " + person.lastName;
    }
}
