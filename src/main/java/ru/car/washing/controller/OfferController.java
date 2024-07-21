package ru.car.washing.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.car.washing.model.Offer;
import ru.car.washing.services.OfferService;

import java.util.List;

@RestController
@RequestMapping
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        if (offers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}/edit")
    public Offer getOfferById(@PathVariable("id") Long id) {
        Offer offer = offerService.getOffer(id);
        return offer;
    }
}
