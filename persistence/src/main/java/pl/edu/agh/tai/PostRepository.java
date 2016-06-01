package pl.edu.agh.tai;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
    // intentionally left blank
}
