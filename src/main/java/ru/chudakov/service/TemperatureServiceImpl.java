package ru.chudakov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.repository.TemperatureRepository;

import java.util.List;

@Service
public class TemperatureServiceImpl implements TemperatureService {
    private final TemperatureRepository repository;

    @Autowired
    public TemperatureServiceImpl(final TemperatureRepository repository){
        this.repository = repository;
    }

    @Override
    public Temperature saveTemperature(Temperature temperature) {
        return repository.save(temperature);
    }

    @Override
    public List<Temperature> getLast() {
        return (List<Temperature>) repository.getLast();
    }
}
