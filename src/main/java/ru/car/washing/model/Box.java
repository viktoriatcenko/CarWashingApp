package ru.car.washing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "box")
    private List<Offer> slots;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
