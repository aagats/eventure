package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.persistence.entitites.PostEntity;

import javax.transaction.Transactional;


@Transactional
public interface PostRepository extends CrudRepository<PostEntity, Long> {

}