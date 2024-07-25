package ru.car.washing.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.car.washing.dto.PersonDTO;
import ru.car.washing.model.Person;
import ru.car.washing.model.Role;
import ru.car.washing.repositories.PersonRepository;

import java.util.Date;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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
        p.setPassword(passwordEncoder.encode(p.getPassword()));
    }

    public PersonDTO convertFromPersonToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertFromDTOToPerson(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        enrich(person);
        return person;
    }

}
