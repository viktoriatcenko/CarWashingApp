package ru.car.washing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.car.washing.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
