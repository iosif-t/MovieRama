package project.workable.movieRama.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.domain.Movie;
import project.workable.movieRama.domain.Vote;
import project.workable.movieRama.domain.VoteType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class VoteRepositoryTest {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findVoteByMovieIdAndUsernameAndType() {
        MUser user = new MUser("David","7");
        userRepository.save(user);
        Movie movie = new Movie();
        movie.setUser(user);
        movieRepository.save(movie);
        user.getMovies().add(movie);
        userRepository.save(user);

        Vote vote = new Vote("John", VoteType.HATE);
        vote.setMovie(movie);
        voteRepository.save(vote);
        Optional<Vote> underTest = voteRepository.findVoteByMovieIdAndUsernameAndType(movie.getId(), vote.getUsername(), vote.getType());

        assertTrue(underTest.isPresent());
        assertEquals(vote.getId(),underTest.get().getId());
    }

}