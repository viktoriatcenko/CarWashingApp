package ru.car.washing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // 15.04.2021-12:00:00
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

    @Column(name = "is_booked")
    private Boolean isBooked;

    @Column(name = "discount_min")
    private Integer discountMin;

    @Column(name = "discount_max")
    private Integer discountMax;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    @JsonIgnore
    private Box box;

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", hourOfStart='" + hourOfStart + '\'' +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                ", isOfferRealized=" + isOfferRealized +
                ", isConfirmed=" + isConfirmed +
                ", isBooked=" + isBooked +
                ", discountMin=" + discountMin +
                ", discountMax=" + discountMax;
    }
}
