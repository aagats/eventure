package pl.edu.agh.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.model.Place;
import pl.edu.agh.tai.persistence.PlaceRepository;
import pl.edu.agh.tai.persistence.dtos.PlaceDto;
import pl.edu.agh.tai.persistence.entitites.PlaceEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "api/places", method = RequestMethod.POST)
    public void createPlace(@RequestBody PlaceDto place) {
        placeRepository.save(new PlaceEntity(place.getName(), place.getCity(), place.getStreet(), place.getBuildingNumber()));
    }

    @RequestMapping(path = "api/places", method = RequestMethod.GET)
    public List<Place> showPlaces() {
        Iterable<PlaceEntity> placeRepositoryAll = placeRepository.findAll();
        List<Place> places = new ArrayList<>();
        for (PlaceEntity placeEntity : placeRepositoryAll) {
            places.add(new Place(placeEntity.getId(), placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber()));
        }
        return places;
    }

    @RequestMapping(path = "api/places/{id}", method = RequestMethod.GET)
    public Place getPlace(@PathVariable(value = "id") long id) {
        //TODO: null pointer exception
        PlaceEntity placeEntity = placeRepository.findOne(id);
        return new Place(placeEntity.getId(), placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber());
    }

}

