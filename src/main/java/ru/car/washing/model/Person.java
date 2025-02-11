package ru.car.washing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<Box> reservedBoxes;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "can_give_discount")
    private Boolean isAbleToMakeDiscount;

    @Column(name = "removed", nullable = false)
    private Boolean isRemoved;

    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", deletedAt=" + deletedAt +
                ", isAbleToMakeDiscount=" + isAbleToMakeDiscount +
                ", isRemoved=" + isRemoved +
                ", password='" + password + '\'' +
                '}';
    }
}
