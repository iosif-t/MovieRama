package project.workable.movieRama.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareConcurrentModel;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.domain.Movie;
import project.workable.movieRama.repositories.UserRepository;
import project.workable.movieRama.services.MovieService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    private final MovieService movieService = mock(MovieService.class);

    private final UserRepository userRepository = mock(UserRepository.class);

    private MovieController movieController;

    private Model model;


    @BeforeEach
    void setUp() {
        movieController = new MovieController(movieService,userRepository);
        model = new BindingAwareConcurrentModel();
    }

    @Test
    void mockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
               .andExpect(view().name("main/page"));
    }

    @Test
    void getMovies() {
        movieController.getMovies(model);
        verify(movieService).getMovies();
    }

    @Test
    void sortByLike() {
        movieController.sortByLike(model);
        verify(movieService).getMoviesSortedByLike();
    }

    @Test
    void sortByHate() {
        movieController.sortByHate(model);
        verify(movieService).getMoviesSortedByHate();
    }

    @Test
    void sortByDate() {
        movieController.sortByDate(model);
        verify(movieService).getMoviesSortedByDate();
    }

    @Test
    void getUserMovies() {
        movieController.getUserMovies(model , 1L);
        verify(movieService).setUserId(1L);
        verify(movieService).getUserMovies();
    }

    @Test
    void voteHandler() {
        assertThrows(NullPointerException.class,
                () -> movieController.voteHandler(0L,"H",model));
    }

    @Test
    void createMovie() {
        movieController.createMovie(model);
        verify(movieService , times(0)).createMovie(new Movie(),new MUser());
    }

}