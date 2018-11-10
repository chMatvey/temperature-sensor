package ru.chudakov.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.chudakov.domain.City;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"latitude", "longitude", "city"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"latitude", "longitude"})
})
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private double latitude;

    @NonNull
    private double longitude;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "city_id", nullable = true)
    private City city;

    public Coordinate(double latitude, double longitude, City city){
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }
}
