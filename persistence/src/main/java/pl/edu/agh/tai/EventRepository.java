package pl.edu.agh.tai;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {
    // intentionally left blank
}
