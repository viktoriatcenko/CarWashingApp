package ru.car.washing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person_app")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
