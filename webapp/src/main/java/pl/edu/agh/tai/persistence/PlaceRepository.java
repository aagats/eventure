package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.persistence.entitites.PlaceEntity;

import javax.transaction.Transactional;

@Transactional
public interface PlaceRepository extends CrudRepository<PlaceEntity, Long> {

}
