package reto.tecnico.com.pichincha.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.security.entity.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.username =:username")
    Mono<User> findByUsername(String username);
}
