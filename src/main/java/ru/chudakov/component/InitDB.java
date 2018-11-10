package ru.chudakov.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.chudakov.domain.City;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.domain.Temperature;
import ru.chudakov.service.CoordinateService;
import ru.chudakov.service.TemperatureService;

import javax.annotation.PostConstruct;

@Component
public class InitDB {
    private CoordinateService coordinateService;
    private TemperatureService temperatureService;

    @Autowired
    public InitDB(CoordinateService coordinateService, TemperatureService temperatureService) {
        this.coordinateService = coordinateService;
        this.temperatureService = temperatureService;
    }

    @PostConstruct
    public void init(){
        if(coordinateService.getCountCoordinateWithCity() == 0){
            Coordinate coordinate = new Coordinate(55.7522200, 37.6155600, new City("Moscow"));
            Coordinate coordinate1 = new Coordinate(59.9386300, 30.3141300, new City("Saint Petersburg"));
            Coordinate coordinate2 = new Coordinate(55.0415000, 82.9346000, new City("Novosibirsk"));
            temperatureService.saveTemperature(new Temperature(0, coordinate));
            temperatureService.saveTemperature(new Temperature(1, coordinate1));
            temperatureService.saveTemperature(new Temperature(2, coordinate2));
        }
    }
}
