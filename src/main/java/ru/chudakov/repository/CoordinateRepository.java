package ru.chudakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chudakov.domain.Coordinate;

import java.util.List;
import java.util.Optional;

public interface CoordinateRepository extends CrudRepository<Coordinate, Integer> {
    Optional<Coordinate> getByLatitudeAndLongitude(double latitude, double longitude);

    int countByCityNotNull();
}
