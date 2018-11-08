package ru.chudakov.service;

import ru.chudakov.domain.City;
import ru.chudakov.domain.Coordinate;

import java.util.Optional;

public interface CoordinateService {
    public Coordinate saveCoordinate(double longitude, double latitude, City city);

    public Optional<Coordinate> getCoordinateByLongAndLat(double longitude, double latitude);
}
