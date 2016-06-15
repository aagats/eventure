package pl.edu.agh.tai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.entities.PostEntity;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    // intentionally left blank
}
