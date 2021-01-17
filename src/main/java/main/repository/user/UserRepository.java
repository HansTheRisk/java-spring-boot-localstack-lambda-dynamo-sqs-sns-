package main.repository.user;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();
    User findByUsername(String username);

}
