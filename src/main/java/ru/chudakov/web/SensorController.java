package ru.chudakov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.service.CoordinateService;
import ru.chudakov.service.TemperatureService;
import ru.chudakov.web.form.Error;
import ru.chudakov.web.form.Result;
import ru.chudakov.web.form.Success;

import java.util.List;

@RestController
public class SensorController {
    private final TemperatureService temperatureService;
    private final CoordinateService coordinateService;

    @Autowired
    public SensorController(TemperatureService temperatureService, CoordinateService coordinateService) {
        this.temperatureService = temperatureService;
        this.coordinateService = coordinateService;
    }

    @GetMapping("/list")
    public List<Temperature> getLastInput() {
        return temperatureService.getLast();
    }

    @PreAuthorize("hasAnyRole('SENSOR')")
    @PostMapping("/send")
    public Result sendData(@RequestBody Temperature temperature){
        Result result;
        double degrees = temperature.getDegrees();
        double latitude = temperature.getCoordinate().getLatitude();
        double longitude = temperature.getCoordinate().getLongitude();
        if (degrees > 60 || degrees < -100 || latitude < -90 || latitude > 90 || longitude < -180 || latitude > 180) {
            result = new Error("Data is incorrect!");
        } else {
            result = new Success<Temperature>(temperatureService.saveTemperature(
                    new Temperature(degrees, coordinateService.getCoordinateByLatitudeAndLongitude(latitude, longitude)
                            .orElse(new Coordinate(latitude, longitude)))));
        }
        return result;
    }
}
