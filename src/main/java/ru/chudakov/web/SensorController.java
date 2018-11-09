package ru.chudakov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.service.CityService;
import ru.chudakov.service.CoordinateService;
import ru.chudakov.service.TemperatureService;

import java.util.List;

@RestController
public class SensorController {
    private final TemperatureService temperatureService;
    private final CoordinateService coordinateService;

    @Autowired
    public SensorController(TemperatureService temperatureService, CoordinateService coordinateService){
        this.temperatureService = temperatureService;
        this.coordinateService = coordinateService;
    }

    @GetMapping("/list")
    public List<Temperature> getLastInput(){
        return temperatureService.getLast();
    }

    @PostMapping("/send")
    public Temperature sendData(@RequestBody double degree,
                                @RequestBody double latitude,
                                @RequestBody double longitude){
        return temperatureService.saveTemperature(degree,
                coordinateService.getCoordinateByLatitudeAndLongitude(latitude, longitude)
                        .orElse(new Coordinate(latitude, longitude, null)));
    }
}
