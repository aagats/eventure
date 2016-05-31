package pl.edu.agh.tai;

import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<PlaceEntity, Long> {
    // intentionally left blank
}
