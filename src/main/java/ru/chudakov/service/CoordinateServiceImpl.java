package ru.chudakov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chudakov.domain.City;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.repository.CoordinateRepository;

import java.util.Optional;

@Service
public class CoordinateServiceImpl implements CoordinateService {
    private CoordinateRepository repository;

    @Autowired
    public CoordinateServiceImpl(CoordinateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Coordinate saveCoordinate(double latitude, double longitude, City city) {
        return repository.save(new Coordinate(latitude, longitude, city));
    }

    @Override
    public Optional<Coordinate> getCoordinateByLatitudeAndLongitude(double latitude, double longitude) {
        return repository.getByLatitudeAndLongitude(longitude, latitude);
    }
}
