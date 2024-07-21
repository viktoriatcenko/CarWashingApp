package ru.car.washing.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/edit/{id}")
    public Offer getOfferById(@PathVariable("id") Long id) {
        Offer offer = offerService.getOffer(id);
        return offer;
    }

    @PostMapping("/book")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer newOffer = offerService.createOffer(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOffer);
    }

//    @DeleteMapping("/unbook/{id}")
//    public ResponseEntity<Response> deleteOffer(@PathVariable("id") Long id) {
//        if (offerService.safeDeleteOffer(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Неверно заданный id для удаления");
//        }
//        return ResponseEntity.accepted().build();
//    }
}
