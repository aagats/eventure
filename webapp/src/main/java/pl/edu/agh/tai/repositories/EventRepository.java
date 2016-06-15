package pl.edu.agh.tai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.entities.EventEntity;

public interface EventRepository extends CrudRepository<EventEntity, Long> {
    // intentionally left blank
}
