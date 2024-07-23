package ru.car.washing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CarWashingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarWashingAppApplication.class, args);
    }

}
