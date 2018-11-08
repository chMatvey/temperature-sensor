package ru.chudakov.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.chudakov.domain.City;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true)
    private double longitude;

    @NonNull
    @Column(unique = true)
    private double latitude;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = true)
    private City city;

//    public Coordinate(double longitude, double latitude, City city){
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.city = city;
//    }
}
