package ru.car.washing.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.car.washing.model.Person;
import ru.car.washing.model.Role;
import ru.car.washing.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        enrich(person);
        personRepository.save(person);
    }

    private void enrich(Person p) {
        p.setCreatedAt(new Date());
        p.setCreatedBy("Self");
        p.setRole(Role.ROLE_USER);
        p.setIsRemoved(false);
    }

}
