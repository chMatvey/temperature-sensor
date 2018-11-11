package ru.chudakov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.repository.CoordinateRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TemperatureServiceTest {

    @Autowired
    CoordinateRepository repository;

    @Autowired
    private TemperatureService service;

    @Test
    public void whenTemperatureAddToDB() {
        Temperature temperature = service.saveTemperature(new Temperature(1, repository
                .getByLatitudeAndLongitude(1, 1)
                .orElse(new Coordinate(1,1))
        ));
        assertTrue(service.getLast().contains(temperature));
    }
}
