package ru.chudakov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chudakov.domain.City;
import ru.chudakov.repository.CityRepository;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<City> getCityByCoordinate(int coordinateId) {
        return repository.findById(coordinateId);
    }
}
