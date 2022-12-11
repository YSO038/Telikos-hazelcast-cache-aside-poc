package com.example.Telikoshazelcastcacheasidepoc;


import com.example.Telikoshazelcastcacheasidepoc.repository.PersonRepository;
import com.example.Telikoshazelcastcacheasidepoc.service.PersonService;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelikosHazelcastCacheAsidePocApplication {

	@Bean
	public PersonService service(HazelcastInstance instance, PersonRepository repository) {
		return new PersonService(instance.getMap("persons"), repository);
	}
	public static void main(String[] args) {
		SpringApplication.run(TelikosHazelcastCacheAsidePocApplication.class, args);
	}

}
