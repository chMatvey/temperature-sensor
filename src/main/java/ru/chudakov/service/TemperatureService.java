package ru.chudakov.service;

import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;

import java.util.List;

public interface TemperatureService {
    public Temperature saveTemperature(double degrees, Coordinate coordinate);

    public List<Temperature> getLast();
}
