package project.workable.movieRama.repositories;

import org.springframework.data.repository.CrudRepository;
import project.workable.movieRama.domain.Movie;

/**
 * Repository for {@link Movie}
 */
public interface MovieRepository extends CrudRepository<Movie,Long> {
    //EMPTY
}
