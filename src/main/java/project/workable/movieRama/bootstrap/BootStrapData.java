package project.workable.movieRama.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.domain.Movie;
import project.workable.movieRama.domain.Vote;
import project.workable.movieRama.domain.VoteType;
import project.workable.movieRama.repositories.MovieRepository;
import project.workable.movieRama.repositories.UserRepository;
import project.workable.movieRama.repositories.VoteRepository;

import java.util.Date;

/**
 * Inits data to h2 db
 */
@Component
public class BootStrapData implements CommandLineRunner {

    /**
     * repository for user
     */
    private final UserRepository userRepository;

    /**
     * repository for movie
     */
    private  final MovieRepository movieRepository;

    /**
     * repository for vote
     */
    private final VoteRepository voteRepository;

    /**
     * Constructor
     * @param userRepository
     * @param movieRepository
     * @param voteRepository
     */
    public BootStrapData(UserRepository userRepository, MovieRepository movieRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        MUser firstUser = new MUser("iosif" , "ela");

        userRepository.save(firstUser);

        Movie firstMovie = new Movie("Fight Club","Description",new Date());
        firstMovie.setUser(firstUser);

        firstUser.getMovies().add(firstMovie);

        movieRepository.save(firstMovie);

        Vote firstVote = new Vote("ela",VoteType.HATE);

        voteRepository.save(firstVote);

        firstMovie.getVotes().add(firstVote);
        movieRepository.save(firstMovie);

        userRepository.save(firstUser);

        System.out.println("Number of users: " + userRepository.count());
        System.out.println("Number of movies: " + movieRepository.count());
        System.out.println("Number of votes: " + voteRepository.count());
    }

}
