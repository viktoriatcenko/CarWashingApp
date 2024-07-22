package ru.car.washing.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.car.washing.exceptions.PersonNotCreatedException;
import ru.car.washing.model.Person;
import ru.car.washing.services.PersonService;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> savePerson(@RequestBody @Valid Person person,
                                                 BindingResult bindingResult) {
        checkForErrors(bindingResult);
        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private void checkForErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError :
                    fieldErrors) {
                stringBuilder.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new PersonNotCreatedException(stringBuilder.toString());
        }
    }
}
