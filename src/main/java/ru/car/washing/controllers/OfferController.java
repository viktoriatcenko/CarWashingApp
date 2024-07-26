package ru.car.washing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.car.washing.dto.OfferDTO;
import ru.car.washing.model.Box;
import ru.car.washing.model.Offer;
import ru.car.washing.model.Person;
import ru.car.washing.services.OfferService;
import ru.car.washing.services.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping
public class OfferController {

    private final OfferService offerService;
    private final PersonService personService;

    @Autowired
    public OfferController(OfferService offerService, PersonService personService) {
        this.offerService = offerService;
        this.personService = personService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        List<OfferDTO> offers = offerService.getAllOffers();
        if (offers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(offers);
    }

    @PostMapping("/edit/{id}")
    public Offer getOfferById(@PathVariable("id") Long id, @RequestBody Offer offer) {
        offerService.updateOffer(id, offer);
        return offer;
    }

    @PostMapping("/book")
    public ResponseEntity<Offer> createOffer(@RequestBody OfferDTO offerDTO) {
        Offer newOffer = offerService.createOffer(offerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOffer);
    }

    @PostMapping("/unbook/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable("id") Long id) {
        if (!offerService.safeDeleteOffer(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<List<Offer>> getAllRealizedOffers(@PathVariable("id") Long id) {
        Person person = personService.findById(id);
        List<Offer> offers = new ArrayList<>();
//        offers.stream()
//                .filter(Offer::getIsOfferRealized)
//                .forEach(offers::add);
//        List<Stream<Offer>> collect = person.getReservedBoxes().stream()
//                .map(box -> box.getSlots().stream().filter(Offer::getIsOfferRealized))
//                .collect(Collectors.toList());
        for (Box box : person.getReservedBoxes()) {
            box.getSlots().forEach(offer -> {
                if (offer.getIsOfferRealized()) {
                    offers.add(offer);
                }
            });
        }

        return ResponseEntity.ok(offers);
    }

    @GetMapping("/all-services")
    public ResponseEntity<List<String>> getAllOffersByName() {
        List<String> offers = offerService.getAllOffers().stream()
                .map(OfferDTO::getName)
                .collect(Collectors.toList());
        if (offers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(offers);
    }
}
