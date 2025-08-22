package org.example.environement.controller;

import org.example.environement.dto.observation.ObservationDtoReceive;
import org.example.environement.dto.observation.ObservationDtoResponse;
import org.example.environement.service.ObservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private final ObservationService observationService;

    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @GetMapping
    public ResponseEntity<List<ObservationDtoResponse>> getObservation (@RequestParam int pageSize, @RequestParam int pageNumber){
        return ResponseEntity.ok(observationService.get(pageSize,pageNumber));
    }

    @PostMapping
    public ResponseEntity<ObservationDtoResponse> create(@RequestBody ObservationDtoReceive observationDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(observationService.create(observationDtoReceive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservationDtoResponse> get(@PathVariable long id){
        return ResponseEntity.ok(observationService.get(id));
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<ObservationDtoResponse>> getByLocation(@RequestParam String location) {
        return ResponseEntity.ok(observationService.getByLocation(location));
    }

    @GetMapping("/by-species")
    public ResponseEntity<List<ObservationDtoResponse>> getBySpecie(@RequestParam Long id) {
        return ResponseEntity.ok(observationService.getBySpecie(id));
    }
}
