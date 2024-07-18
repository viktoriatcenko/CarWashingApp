package ru.car.washing.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person_app")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    private Role role;
    @Column(name = "box")
    private List<Box> reservedBoxes;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "can_give_discount")
    private Boolean isAbleToMakeDiscount;
    @Column(name = "removed")
    private Boolean isRemoved;
    @Column(name = "password")
    private String password;


}
