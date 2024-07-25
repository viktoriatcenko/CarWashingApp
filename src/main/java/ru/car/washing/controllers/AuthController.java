package ru.car.washing.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.car.washing.services.PersonService;
import ru.car.washing.validation.PersonValidator;
import ru.car.washing.dto.PersonDTO;
import ru.car.washing.model.Person;
import ru.car.washing.util.JWTUtil;


import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;
    private final JWTUtil jwtUtil;
    private final ModelMapper mapper;
    private final PersonValidator personValidator;


    @Autowired
    public AuthController(PersonService personService, JWTUtil jwtUtil, ModelMapper mapper, PersonValidator personValidator) {
        this.personService = personService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String login() {
        return "successfully login";
    }

    @PostMapping("/registration")
    public Map<String, String> registration(@RequestBody @Valid  PersonDTO personDTO,
                            BindingResult bindingResult) {

        Person person = personService.convertFromDTOToPerson(personDTO);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "error body");
        }

        personService.save(person);

        String token = jwtUtil.generateToken(person.getName());

        return Map.of("jwt-token", token);
    }
}
