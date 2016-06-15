package pl.edu.agh.tai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.entities.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    // intentionally left blank
}
