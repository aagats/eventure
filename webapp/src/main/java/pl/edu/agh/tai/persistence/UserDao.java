package pl.edu.agh.tai.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.persistence.entitites.UserEntity;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<UserEntity, String> {
}
