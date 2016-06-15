package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.persistence.entitites.PlaceEntity;

import javax.transaction.Transactional;

@Transactional
public interface PlaceDao extends CrudRepository<PlaceEntity, Long> {
}
