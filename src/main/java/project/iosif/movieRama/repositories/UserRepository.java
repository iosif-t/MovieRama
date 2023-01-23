package project.iosif.movieRama.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.iosif.movieRama.domain.MUser;

import java.util.Optional;

/**
 * Repository for {@link MUser}
 */
public interface UserRepository extends JpaRepository<MUser,Long> {
    Optional<MUser> findUserByUsername(String username);
}
