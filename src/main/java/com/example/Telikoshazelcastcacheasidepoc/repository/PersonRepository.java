package com.example.Telikoshazelcastcacheasidepoc.repository;

import com.example.Telikoshazelcastcacheasidepoc.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Integer> {
   /* @Query("SELECT ")
    Mono<Person> findById(Mono<Integer> id);*/
}