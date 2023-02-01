package pl.fg.kursspringmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fg.kursspringmd.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
