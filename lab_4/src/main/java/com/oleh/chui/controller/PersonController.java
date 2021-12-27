package com.oleh.chui.controller;

import com.oleh.chui.model.entity.Person;
import com.oleh.chui.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void create(@RequestBody Person person) {
        personService.create(person);
    }

}
