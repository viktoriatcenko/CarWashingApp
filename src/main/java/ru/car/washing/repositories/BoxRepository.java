package ru.car.washing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.car.washing.model.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
}
