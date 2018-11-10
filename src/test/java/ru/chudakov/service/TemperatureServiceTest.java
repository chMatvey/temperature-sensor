package ru.chudakov.service;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureServiceTest {

    @Autowired
    CoordinateRepository repository;

    @Autowired
    private TemperatureService service;

    @Test
    public void whenTemperatureAddDB() {
        Temperature temperature = service.saveTemperature(new Temperature(1,
                repository.getByLatitudeAndLongitude(1, 1)
                        .orElse(new Coordinate(1,1))));
        Temperature temperature1 = service.saveTemperature(new Temperature(1,
                repository.getByLatitudeAndLongitude(1, 1)
                        .orElse(new Coordinate(1,1))));
        //assertTrue(temperatureService.getLast().contains(temperature));
    }
}
