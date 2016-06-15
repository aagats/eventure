package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.model.CustomUser;
import pl.edu.agh.tai.persistence.entitites.CommentEntity;

import javax.transaction.Transactional;
import javax.xml.stream.events.Comment;
import java.util.List;

@Transactional
public interface CommentDao extends CrudRepository<CommentEntity, Long> {

    List<Comment> findByAuthor(CustomUser author);
}
