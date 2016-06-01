package pl.edu.agh.tai;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    // intentionally left blank
}
