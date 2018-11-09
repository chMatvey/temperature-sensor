package ru.chudakov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.chudakov.domain.City;
import ru.chudakov.service.CoordinateService;

@RestController
public class TestController {
    private CoordinateService coordinateService;

    @Autowired
    public TestController(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    @GetMapping("/coordinate")
    public void loadCityCoordinate(@RequestParam String name,
                                   @RequestParam double latitude,
                                   @RequestParam double longitude) {
        coordinateService.saveCoordinate(latitude, longitude, new City(name));
    }
}
