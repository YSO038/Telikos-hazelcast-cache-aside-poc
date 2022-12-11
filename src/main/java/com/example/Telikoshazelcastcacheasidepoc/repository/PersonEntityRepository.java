package com.example.Telikoshazelcastcacheasidepoc.repository;

import com.example.Telikoshazelcastcacheasidepoc.model.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonEntityRepository extends ReactiveCrudRepository<PersonEntity, Integer> {
    //Mono<Person> findById(Mono<Integer> id);
}