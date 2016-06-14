package pl.edu.agh.tai;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity, Long>{
    // intentionally left blank
}
