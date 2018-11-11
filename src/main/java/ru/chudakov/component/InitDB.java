package ru.chudakov.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.chudakov.domain.City;
import ru.chudakov.domain.Coordinate;
import ru.chudakov.service.CoordinateService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDB {
    private CoordinateService coordinateService;

    @Autowired
    public InitDB(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    @PostConstruct
    public void init(){
        if(coordinateService.getCountCoordinateWithCity() == 0){
            List<Coordinate> list = new ArrayList<>();
            list.add(new Coordinate(55.7522200, 37.6155600, new City("Moscow")));
            list.add(new Coordinate(59.9386300, 30.3141300, new City("Saint Petersburg")));
            list.add(new Coordinate(55.0415000, 82.9346000, new City("Novosibirsk")));
            coordinateService.saveAll(list);
        }
    }
}
