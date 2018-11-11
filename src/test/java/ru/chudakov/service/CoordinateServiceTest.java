package ru.chudakov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.repository.TemperatureRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CoordinateServiceTest {

    @Autowired
    TemperatureRepository repository;

    @Autowired
    CoordinateService service;

    @Test
    public void whenCoordinateAddToDBThenReturnTrue() {
        Temperature temperature = repository.save(new Temperature(1, service
                .getCoordinateByLatitudeAndLongitude(1, 1)
                .orElse(new Coordinate(1, 1))
        ));
        Coordinate coordinate = service.getCoordinateByLatitudeAndLongitude(
                temperature.getCoordinate().getLatitude(), temperature.getCoordinate().getLongitude()
        ).orElse(null);
        assertEquals(temperature.getCoordinate(), coordinate);
    }

    @Test
    public void whenCoordinateNotAddToDBThenReturnFalse() {
        Coordinate coordinate = new Coordinate(1, 1);
        Coordinate coordinate1 = service.getCoordinateByLatitudeAndLongitude(
                coordinate.getLatitude(), coordinate.getLongitude()
        ).orElse(null);
        assertNotEquals(coordinate, coordinate1);
    }
}
