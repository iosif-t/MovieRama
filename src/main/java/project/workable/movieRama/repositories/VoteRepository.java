package project.workable.movieRama.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.workable.movieRama.domain.Vote;
import project.workable.movieRama.domain.VoteType;

import java.util.Optional;

/**
 * Repository for {@link Vote}
 */
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findVoteByMovieIdAndUsernameAndType(Long movieId, String username, VoteType type);
}
