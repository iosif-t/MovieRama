package project.iosif.movieRama.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.iosif.movieRama.domain.MUser;
import project.iosif.movieRama.domain.Movie;
import project.iosif.movieRama.domain.Vote;
import project.iosif.movieRama.domain.VoteType;

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