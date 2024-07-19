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

//    @Bean
//    public Flyway flyway() {
//        return Flyway.configure()
//                .dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
//                .locations("classpath:db/migration")
//                .load();
//    }
//
//    /**
//     * Override default flyway initializer to do nothing
//     */
//    @Bean
//    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, (f) ->{} );
//    }
//
//
//    /**
//     * Create a second flyway initializer to run after jpa has created the schema
//     */
//    @Bean
//    @DependsOn("entityManagerFactory")
//    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
//        return new FlywayMigrationInitializer(flyway, null);
//    }

}
