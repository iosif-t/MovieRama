package project.workable.movieRama.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.domain.Movie;
import project.workable.movieRama.domain.VoteType;
import project.workable.movieRama.repositories.UserRepository;
import project.workable.movieRama.services.MovieService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for {@link Movie}
 */
@Controller
public class MovieController {

    /**
     * page constant
     */
    public static final String PAGE = "main/page";

    /**
     * redirect to / constant
     */
    public static final String REDIRECT_MAIN = "redirect:/";

    /**
     * movie constant
     */
    public static final String MOVIES_FORM = "movies/movie";

    /**
     * movies constant
     */
    public static final String MOVIE_LIST = "movies";

    /**
     * movie service
     */
    public final MovieService movieService;

    /**
     * user repository
     */
    public final UserRepository userRepository;

    /**
     * Constructor
     * @param movieService
     * @param userRepository
     */
    public MovieController(MovieService movieService, UserRepository userRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;
    }

    /**
     * @param model
     * @return Returns PAGE
     */
    @RequestMapping("/")
    public String getMovies(Model model) {
        movieService.setUserId(null);
        List<Movie> movies = movieService.getMovies();
        model.addAttribute(MOVIE_LIST, movies);
        return PAGE;
    }

    /**
     * @param model
     * @return Returns PAGE
     */
    @GetMapping("/sortByLike")
    public String sortByLike(Model model) {
        model.addAttribute(MOVIE_LIST, movieService.getMoviesSortedByLike());
        return PAGE;
    }

    /**
     * @param model
     * @return Returns PAGE
     */
    @GetMapping("/sortByHate")
    public String sortByHate(Model model) {
        model.addAttribute(MOVIE_LIST, movieService.getMoviesSortedByHate());
        return PAGE;
    }

    /**
     * @param model
     * @return Returns PAGE
     */
    @GetMapping("/sortByDate")
    public String sortByDate(Model model) {
        model.addAttribute(MOVIE_LIST, movieService.getMoviesSortedByDate());
        return PAGE;
    }

    /**
     * @param model
     * @param userId
     * @return Returns PAGE
     */
    @GetMapping("/userMovies")
    public String getUserMovies(Model model, @RequestParam(name = "userId") Long userId) {
        movieService.setUserId(userId);
        model.addAttribute(MOVIE_LIST, movieService.getUserMovies());
        return PAGE;
    }

    /**
     * @param movieId
     * @param type
     * @param model
     * @return Returns REDIRECT_MAIN
     */
    @GetMapping("/vote")
    public String voteHandler(@RequestParam(name = "movieId") Long movieId ,
                              @RequestParam(name = "type") String type , Model model) {
        Movie movie = movieService.findMovieById(movieId);
        currentUser().ifPresent((user) -> {
            movieService.voteHandler(movie,user,VoteType.valueOf(type));
            model.addAttribute(MOVIE_LIST,movieService.getMovies());
        });
        return PAGE;
    }

    /**
     * @param movie
     * @return Returns REDIRECT_MAIN
     */
    @PostMapping(path = "/movies")
    public String createMovie(Movie movie) {
        Optional<MUser> user = currentUser(); // impossible for user to not exist
        user.ifPresent((currUser) -> movieService.createMovie(movie,currUser));
        return REDIRECT_MAIN;
    }

    /**
     * @param model
     * @return Returns MOVIES_FORM
     */
    @GetMapping(path = "/movies")
    public String createMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return MOVIES_FORM;
    }

    /**
     * Gets the current logged in user
     * @return Returns the logged in user
     */
    private Optional<MUser> currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUsername(username);
    }

}
