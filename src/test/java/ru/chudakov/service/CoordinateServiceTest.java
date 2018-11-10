package ru.chudakov.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.repository.CoordinateRepository;
import ru.chudakov.repository.TemperatureRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoordinateServiceTest {

    @Autowired
    TemperatureRepository repository;

    @Autowired
    CoordinateService service;

    @Test
    public void whenCoordinateAddToDBThenReturnTrue() {
        Temperature temperature = repository.save(new Temperature(1, service
                .getCoordinateByLatitudeAndLongitude(1,1)
                .orElse(new Coordinate(1,1))));
        Coordinate coordinate = service.getCoordinateByLatitudeAndLongitude(
                temperature.getCoordinate().getLatitude(), temperature.getCoordinate().getLongitude())
                .orElse(null);
        assertEquals(temperature.getCoordinate(), coordinate);
    }

    @Test
    public void whenCoordinateNotAddToDBThenReturnFalse() {
        Temperature temperature = new Temperature(1, service
                .getCoordinateByLatitudeAndLongitude(1,1)
                .orElse(new Coordinate(1,1)));
        Coordinate coordinate = service.getCoordinateByLatitudeAndLongitude(
                temperature.getCoordinate().getLatitude(), temperature.getCoordinate().getLongitude())
                .orElse(null);
        assertNotEquals(temperature.getCoordinate(), coordinate);
    }
}
