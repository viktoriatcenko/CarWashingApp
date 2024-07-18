package ru.car.washing.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "Box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private Integer number;

    @Column(name = "slots")
    private Map<Date,List<Service>> slots;
}
