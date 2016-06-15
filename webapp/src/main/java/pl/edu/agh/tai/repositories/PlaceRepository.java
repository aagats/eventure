package pl.edu.agh.tai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.entities.PlaceEntity;


public interface PlaceRepository extends CrudRepository<PlaceEntity, Long> {
    // intentionally left blank
}
