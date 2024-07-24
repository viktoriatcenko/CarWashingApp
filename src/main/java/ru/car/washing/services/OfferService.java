package ru.car.washing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.car.washing.model.Offer;
import ru.car.washing.repositories.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;


    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer getOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElse(null);
        return offer;
    }

    public List<Offer> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers;
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public void updateOffer(Long id, Offer offer) {
        Offer oldOffer = offerRepository.findById(id).orElse(null);
        Optional<Offer> byHourOfStartEndingWith = offerRepository.findByHourOfStartEndingWith(offer.getHourOfStart());
        if (byHourOfStartEndingWith.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "В базе еще нет окна с таким временем");
        } else if (oldOffer != null && !byHourOfStartEndingWith.get().getIsBooked()) {
            oldOffer.setHourOfStart(offer.getHourOfStart());
            oldOffer.setName(offer.getName());
            offer.setIsBooked(false);
            offerRepository.save(offer);
            offerRepository.save(oldOffer);
        }
    }

    public boolean safeDeleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if (offer != null) {
            offer.setIsConfirmed(false);
            offerRepository.save(offer);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "В базе нет заказа с таким ID");
        }
    }
}
