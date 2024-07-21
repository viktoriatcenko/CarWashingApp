package ru.car.washing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.car.washing.model.Offer;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByHourOfStartStartingWith(String hourOfStartStartingWith);

//   Optional<Offer> findOffersByIdAndIsConfirmedIsTrue(Long id);
//
//   boolean existsOfferByIsConfirmedIsTrue(Long id);
}
