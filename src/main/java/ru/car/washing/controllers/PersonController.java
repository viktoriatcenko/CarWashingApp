package ru.car.washing.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.car.washing.dto.AuthDTO;
import ru.car.washing.exceptions.PersonNotCreatedException;
import ru.car.washing.model.Person;
import ru.car.washing.services.PersonService;
import ru.car.washing.util.JWTUtil;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

}
