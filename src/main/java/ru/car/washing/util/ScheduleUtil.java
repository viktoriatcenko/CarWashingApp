package ru.car.washing.util;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.car.washing.model.Offer;
import ru.car.washing.repositories.OfferRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ScheduleUtil {

    private final OfferRepository offerRepository;

    @Autowired
    public ScheduleUtil(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

//    @Scheduled(initialDelay = 3000)
//    public void test() {
//        String date = LocalDateTime.now().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        var list = offerRepository.findByHourOfStartStartingWith(date);
//    }

}
