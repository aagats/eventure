package tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.Place;
import pl.edu.agh.tai.PlaceDto;
import pl.edu.agh.tai.PlaceEntity;
import pl.edu.agh.tai.PlaceRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @RequestMapping(path = "api/places", method = RequestMethod.POST)
    public void createPlacePlace(@RequestBody PlaceDto place) {
        placeRepository.save(new PlaceEntity(place.getName(), place.getCity(), place.getStreet(), place.getBuildingNumber()));
    }

    @RequestMapping(path = "api/places", method = RequestMethod.GET)
    public List<Place> showPlaces() {
        Iterable<PlaceEntity> placeRepositoryAll = placeRepository.findAll();
        List<Place> places = new ArrayList<>();
        for (PlaceEntity placeEntity : placeRepositoryAll) {
            places.add(new Place(placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber()));
        }
        return places;
    }

    @RequestMapping(path = "api/places/{id}", method = RequestMethod.GET)
    public Place getPlace(@PathVariable(value = "id") long id) {
        //TODO: null pointer exception
        PlaceEntity placeEntity = placeRepository.findOne(id);
        return new Place(placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber());
    }

}

