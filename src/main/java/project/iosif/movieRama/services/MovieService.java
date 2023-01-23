package project.iosif.movieRama.services;


import org.springframework.stereotype.Service;
import project.iosif.movieRama.comparators.DateComparator;
import project.iosif.movieRama.comparators.HateComparator;
import project.iosif.movieRama.comparators.LikeComparator;
import project.iosif.movieRama.repositories.MovieRepository;
import project.iosif.movieRama.repositories.UserRepository;
import project.iosif.movieRama.repositories.VoteRepository;
import project.iosif.movieRama.domain.MUser;
import project.iosif.movieRama.domain.Movie;
import project.iosif.movieRama.domain.Vote;
import project.iosif.movieRama.domain.VoteType;
import project.iosif.movieRama.utils.MovieUtils;

import java.util.*;

/**
 * Service for {@link Movie}
 */
@Service
public class MovieService {

    /**
     * movie repository
     */
    public final MovieRepository movieRepository;

    /**
     * user repository
     */
    public final UserRepository userRepository;

    /**
     * vote repository
     */
    public final VoteRepository voteRepository;

    /**
     * id of selected user
     */
    private Long userId = null;

    /**
     * Constructor
     * @param movieRepository
     * @param userRepository
     * @param voteRepository
     */
    public MovieService(MovieRepository movieRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    /**
     * @return Returns the movies of a user
     */
    public List<Movie> getUserMovies() {
        MUser user = userRepository.findById(userId).get();
        return new ArrayList<>(user.getMovies());
    }

    /**
     * Handles the votes
     * @param movie
     * @param user
     * @param type
     */
    public void voteHandler(Movie movie, MUser user, VoteType type) {
        if (!movieBelongToUser(movie, user)) {
            if (VoteType.LIKE.equals(type))
                likeHandler(movie, user);
            else if (VoteType.HATE.equals(type))
                hateHandler(movie, user);
        }
    }

    /**
     * Checks if movie belongs to user
     * @param movie
     * @param user
     * @return Returns true if movie belongs to user
     */
    public boolean movieBelongToUser(Movie movie, MUser user) {
        return movie.getUser().getUsername().equals(user.getUsername());
    }

    /**
     * Handle hates
     * @param movie
     * @param currentUser
     */
    public void hateHandler(Movie movie, MUser currentUser) {
        if (MovieUtils.hasHated(movie, currentUser.getUsername()))
            decreaseVotes(movie, currentUser, VoteType.HATE);
        else {
            increaseVotes(movie, currentUser, VoteType.HATE);
            decreaseVotes(movie, currentUser, VoteType.LIKE);
        }
    }

    /**
     * Handle likes
     * @param movie
     * @param currentUser
     */
    public void likeHandler(Movie movie, MUser currentUser) {
        if (MovieUtils.hasLiked(movie, currentUser.getUsername()))
            decreaseVotes(movie, currentUser, VoteType.LIKE);
        else {
            increaseVotes(movie, currentUser, VoteType.LIKE);
            decreaseVotes(movie, currentUser, VoteType.HATE);
        }
    }

    /**
     * Increases the votes by type (like or hate) of the given user for the given movie
     * @param movie
     * @param currentUser
     * @param type
     */
    public void increaseVotes(Movie movie, MUser currentUser, VoteType type) {
        Vote vote = new Vote(currentUser.getUsername(), type);
        voteRepository.save(vote);
        movie.getVotes().add(vote);
        movieRepository.save(movie);
    }

    /**
     * Decreases the votes by type (like or hate) of the given user for the given movie
     * @param movie
     * @param currentUser
     * @param type
     */
    public void decreaseVotes(Movie movie, MUser currentUser, VoteType type) {
        Optional<Vote> vote = voteRepository.findVoteByMovieIdAndUsernameAndType(movie.getId(),currentUser.getUsername(),type);
        if (vote.isPresent()) {
            movie.getVotes().remove(vote.get());
            movieRepository.save(movie);
        }
    }

    /**
     * Creates new movie
     * @param movie
     * @param user
     */
    public void createMovie(Movie movie, MUser user) {
        movie.setPublicationDate(new Date());
        movie.setUser(user);
        movieRepository.save(movie);
        user.getMovies().add(movie);
        userRepository.save(user);
    }

    /**
     * @param id
     * @return Returns the movie that corresponds to the given id
     */
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).get();
    }

    /**
     * Gets all the movies or the movies of a user
     * @return Returns the movies
     */
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        if (userId != null)
            return getUserMovies();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    /**
     * @return Returns the movies sorted by likes
     */
    public List<Movie> getMoviesSortedByLike() {
        List<Movie> movies = getMovies();
        movies.sort(Comparator.comparing((movie -> movie.getLikes().size())));
        return movies;
    }

    /**
     * @return Returns the movies sorted by hate
     */
    public List<Movie> getMoviesSortedByHate() {
        List<Movie> movies = getMovies();
        movies.sort(Comparator.comparing((movie -> movie.getHates().size())));
        return movies;
    }

    /**
     * @return Returns the movies sorted by date
     */
    public List<Movie> getMoviesSortedByDate() {
        List<Movie> movies = getMovies();
        movies.sort(Comparator.comparing(Movie::getPublicationDate));
        return movies;
    }

    /**
     * Setter for userId
     * @param id
     */
    public void setUserId(Long id) {
        this.userId = id;
    }

}
