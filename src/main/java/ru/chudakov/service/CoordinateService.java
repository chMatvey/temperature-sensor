package ru.chudakov.service;

import ru.chudakov.domain.Coordinate;

import java.util.Optional;

public interface CoordinateService {
    public Optional<Coordinate> getCoordinateByLatitudeAndLongitude(double latitude, double longitude);

    public int getCountCoordinateWithCity();

    public Iterable<Coordinate> saveAll(Iterable<Coordinate> list);
}
