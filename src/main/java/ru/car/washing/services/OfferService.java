package ru.car.washing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.car.washing.model.Offer;
import ru.car.washing.repositories.OfferRepository;

import java.util.List;

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

    public Offer updateOffer(Long id, Offer offer) {
        Offer oldOffer = offerRepository.findById(id).orElse(null);
        if (oldOffer != null) {
            offer.setId(id);

        }
        return offerRepository.save(offer);
    }

//    public Boolean safeDeleteOffer(Long id) {
//        Offer offer = offerRepository.findOffersByIdAndIsConfirmedIsTrue(id).orElse(null);
//        if (offer != null) {
//            offer.setIsConfirmed(true);
//            offerRepository.save(offer);
//            return false;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "В базе нет заказа с таким ID");
//        }
//    }
}
