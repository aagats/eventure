package pl.edu.agh.tai.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.tai.entities.UserEntity;

public interface UserRepository extends CrudRepository <UserEntity, Long>{
    // intentionally left blank
}
