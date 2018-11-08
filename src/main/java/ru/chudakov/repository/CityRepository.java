package ru.chudakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chudakov.domain.City;

public interface CityRepository extends CrudRepository<City, Integer> {
}
