package ru.car.washing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hour_of_start")
    private String hourOfStart;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "name")
    private String name;

    @Column(name = "is_offer_realized")
    private Boolean isOfferRealized;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "discount_min")
    private Integer discountMin;

    @Column(name = "discount_max")
    private Integer discountMax;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private Box box;

}
