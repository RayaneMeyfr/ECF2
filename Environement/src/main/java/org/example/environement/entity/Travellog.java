package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.entity.enums.TravelMode;

import java.time.LocalDate;

import static org.example.environement.entity.enums.TravelMode.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Travellog {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "observation_id", nullable = false)
    private Observation observation;
    private Double distanceKm;
    private TravelMode mode;
    private Double estimatedCo2Kg;

    public TravellogDtoResponse entityToDto() {
        return TravellogDtoResponse.builder()
                .id(this.getId())
                .distanceKm(this.getDistanceKm())
                .mode(this.getMode().toString())
                .estimatedCo2Kg(this.getEstimatedCo2Kg())
                .build();
    }

    public void calculateCO2(){
        switch (mode) {
            case WALKING:
                this.estimatedCo2Kg = this.distanceKm;
                break;
            case BIKE:
                this.estimatedCo2Kg = this.distanceKm;
                break;
            case CAR:
                this.estimatedCo2Kg = this.distanceKm * 0.22;
                break;
            case BUS:
                this.estimatedCo2Kg = this.distanceKm * 0.11;
                break;
            case TRAIN:
                this.estimatedCo2Kg = this.distanceKm * 0.03;
                break;
            case PLANE:
                this.estimatedCo2Kg = this.distanceKm * 0.259;
                break;
            default:
                this.estimatedCo2Kg = 0.0;
        }
    }
}
