package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.persistence.entitites.EventEntity;

import javax.transaction.Transactional;

@Transactional
public interface EventRepository extends CrudRepository<EventEntity, Long> {

}