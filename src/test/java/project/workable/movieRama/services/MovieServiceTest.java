package project.workable.movieRama.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.domain.Movie;
import project.workable.movieRama.domain.Vote;
import project.workable.movieRama.domain.VoteType;
import project.workable.movieRama.repositories.MovieRepository;
import project.workable.movieRama.repositories.UserRepository;
import project.workable.movieRama.repositories.VoteRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);

    private final MovieRepository movieRepository = mock(MovieRepository.class);

    private final VoteRepository voteRepository = mock(VoteRepository.class);

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository,userRepository,voteRepository);
    }

    @Test
    void getUserMovies() {
        movieService.setUserId(2L);
        when(userRepository.findById(2L)).thenReturn(Optional.of(new MUser()));
        movieService.getUserMovies();
        verify(userRepository).findById(2L);
    }

    @Test
    void increaseVotes() {
        movieService.increaseVotes(new Movie(),new MUser(),VoteType.HATE);
        verify(voteRepository).save(new Vote());
        verify(movieRepository).save(new Movie());
    }

    @Test
    void decreaseVotes() {
        movieService.decreaseVotes(new Movie(),new MUser(),VoteType.LIKE);
        verify(voteRepository).findVoteByMovieIdAndUsernameAndType(null,null,VoteType.LIKE);
    }

    @Test
    void createMovie() {
        movieService.createMovie(new Movie(),new MUser());
        verify(movieRepository).save(new Movie());
        verify(userRepository).save(new MUser());
    }

    @Test
    void findMovieById() {
        when(movieRepository.findById(2L)).thenReturn(Optional.of(new Movie()));
        movieService.findMovieById(2L);
        verify(movieRepository).findById(2L);
    }

    @Test
    void getMovies() {
        movieService.setUserId(null);
        movieService.getMovies();
        verify(movieRepository).findAll();
        movieService.setUserId(2L);
        assertThrows(NoSuchElementException.class,
                () -> movieService.getMovies());
    }

}