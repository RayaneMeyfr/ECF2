package org.example.environement.service;

import org.example.environement.dto.specie.SpecieDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.entity.Observation;
import org.example.environement.entity.Travellog;
import org.example.environement.exception.NotFoundException;
import org.example.environement.repository.TravellogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TravellogsService {
    TravellogRepository travellogRepository;


    public TravellogsService(TravellogRepository travellogRepository) {
        this.travellogRepository = travellogRepository;
    }

    public TravellogDtoResponse create(TravellogDtoReceive travellogDtoReceive) {
        return travellogRepository.save(travellogDtoReceive.dtoToEntity()).entityToDto();
    }

    public TravellogDtoStat getStat(Long id) {
        List<TravellogDtoResponse> travelobservation = travellogRepository.
                findTravellogByObservation_Id(id).stream().map(Travellog::entityToDto).toList();

        TravellogDtoStat stat = new TravellogDtoStat();

        for (TravellogDtoResponse travellogDtoResponse : travelobservation) {
            stat.addTotalDistanceKm(travellogDtoResponse.getDistanceKm());
            stat.addTotalEmissionsKg(travellogDtoResponse.getEstimatedCo2Kg());
            stat.addMode(travellogDtoResponse.getMode().toString(),travellogDtoResponse.getDistanceKm());
        }

     return  stat;
    }

    public List<TravellogDtoResponse> get(int nbElement) {
        return travellogRepository.findAll().stream().limit(nbElement).map(Travellog::entityToDto).toList();
    }

    public Map<String, TravellogDtoStat> getStatForUserLastMonth(String name) {
        Map<String, TravellogDtoStat> statMap = new HashMap<>();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        List<TravellogDtoResponse> travelUser = travellogRepository
                .findTravellogByUserForLastMonth(name, oneMonthAgo)
                .stream()
                .map(Travellog::entityToDto)
                .toList();

        TravellogDtoStat userStat = new TravellogDtoStat();

        for (TravellogDtoResponse travellogDto : travelUser) {
            userStat.addTotalDistanceKm(travellogDto.getDistanceKm());
            userStat.addTotalEmissionsKg(travellogDto.getEstimatedCo2Kg());
            userStat.addMode(travellogDto.getMode().toString(), travellogDto.getDistanceKm());
        }

        statMap.put(name, userStat);
        return statMap;
    }


}
