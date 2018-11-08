package ru.chudakov.service;

import ru.chudakov.domain.City;

import java.util.Optional;

public interface CityService {
    public Optional<City> getCityByCoordinate(int coordinateId);
}
