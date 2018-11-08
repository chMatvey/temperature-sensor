package ru.chudakov.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.chudakov.domain.Temperature;

import java.util.List;

public interface TemperatureRepository extends CrudRepository<Temperature, Integer> {
    @Query(value = "SELECT * FROM temperature ORDER BY id LIMIT 10", nativeQuery = true)
    List<Temperature> getLastLimit();
}
