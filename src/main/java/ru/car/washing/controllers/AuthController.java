package ru.car.washing.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.car.washing.dto.AuthDTO;
import ru.car.washing.exceptions.PersonNotCreatedException;
import ru.car.washing.services.PersonService;
import ru.car.washing.validation.PersonValidator;
import ru.car.washing.dto.PersonDTO;
import ru.car.washing.model.Person;
import ru.car.washing.util.JWTUtil;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(PersonService personService, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.personService = personService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> savePerson(@RequestBody @Valid Person person,
                                                 BindingResult bindingResult) {
        checkForErrors(bindingResult);
        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken userToken =
                new UsernamePasswordAuthenticationToken(
                        authDTO.getName(), authDTO.getPassword());
        try {
            authenticationManager.authenticate(userToken);
        } catch (Exception e) {
            return Map.of("error", "incorrect login or password");
        }
        String token = jwtUtil.generateToken(authDTO.getName());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/resset-password")
    public ResponseEntity<HttpStatus> resetPassword(@RequestBody AuthDTO authDTO) {
        personService.resetPassword(authDTO);
        if (authDTO.getName() == null || authDTO.getPassword() == null) {
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else if(!personService.isUserExists(authDTO.getName())){
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
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
