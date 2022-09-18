package project.workable.movieRama.comparators;

import project.workable.movieRama.domain.Movie;

import java.util.Comparator;

/**
 * Comparator for likes field of {@link Movie} class
 */
public class LikeComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getLikes().size(), o2.getLikes().size());
    }

}
