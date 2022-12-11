package com.example.Telikoshazelcastcacheasidepoc.service;


import com.example.Telikoshazelcastcacheasidepoc.dto.PersonDto;
import com.example.Telikoshazelcastcacheasidepoc.model.Person;
import com.example.Telikoshazelcastcacheasidepoc.repository.PersonEntityRepository;
import com.example.Telikoshazelcastcacheasidepoc.repository.PersonRepository;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Slf4j
public class PersonService {

    private static final Logger LOGGER = Loggers.getLogger(PersonService.class);

    private final IMap<Integer, Person> cache;
    private final PersonRepository repository;

    @Autowired
    private PersonEntityRepository personEntityRepository;

    public PersonService(IMap<Integer, Person> cache, PersonRepository repository) {
        this.repository = repository;
        this.cache = cache;
    }

    /**
     * Cache-Aside Pattern
     * @param id
     * @return
     */
    public Mono<PersonDto> findById(Integer id) {
        return Mono.fromCompletionStage(() -> cache.getAsync(id))
                .doOnNext(p -> LOGGER.info("Person with id " + p.getId() + " found in cache"))
                .switchIfEmpty(personEntityRepository.findById(id).map(p -> {
                    Person person = Person.builder().id(p.getId()).
                            firstName(p.getFirstName()).
                            lastName(p.getLastName()).birthdate(p.getBirthdate()).build();
                    cache.putAsync(p.getId(), person);
                    LOGGER.info("Person with id " + p.getId() + " set in cache");
                    return person;
                })).map(person -> {
            return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getBirthdate());
        });
    }

    public Flux<Person> findAll(Sort sort) {
        return repository
                .findAll()
                .doOnNext(p -> cache.putAsync(p.getId(), p));

    }

    public Mono<PersonDto> save(Person person){
        log.info("service method called ...");
        return repository.save(person.setAsNew()).doOnNext(p -> cache.putAsync(p.getId(), p)).map(personNew -> {
            return new PersonDto(personNew.getId(), personNew.getFirstName(), personNew.getLastName(),
                    personNew.getBirthdate());
        });
    }
}