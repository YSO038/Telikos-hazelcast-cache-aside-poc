package com.example.Telikoshazelcastcacheasidepoc.controller;

import com.example.Telikoshazelcastcacheasidepoc.dto.PersonDto;
import com.example.Telikoshazelcastcacheasidepoc.model.Person;
import com.example.Telikoshazelcastcacheasidepoc.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/persons")
    public Flux<Person> getAll()  {
        log.debug("Controller method in getAll");
        return service.findAll(Sort.by("lastName", "firstName"));
    }

    @GetMapping("/person/{id}")
    public Mono<PersonDto> getPersonById(@PathVariable("id")  String id)   {
        log.debug("Controller method in getPersonById");
        return service.findById(Integer.valueOf(id));
    }

    @PostMapping("/person/save")
    public Mono<PersonDto> save(@RequestBody Person person) {
        log.debug("Controller method in save");
        return service.save(person);
    }


}
