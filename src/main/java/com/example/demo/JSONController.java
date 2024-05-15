package com.example.demo;

import etc.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JSONController {

    @GetMapping("/json")
    @ResponseBody
    public Person getJson() {
        Person person = new Person();
        person.setAge(26);
        person.setName("강우");
        return person;
    }
}