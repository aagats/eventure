package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.persistence.entitites.EventEntity;
import pl.edu.agh.tai.persistence.entitites.UserEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EventRepository extends CrudRepository<EventEntity, Long> {
    List<EventEntity> findByObservators(UserEntity user);
}