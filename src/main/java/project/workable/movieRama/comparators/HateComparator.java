package project.workable.movieRama.comparators;

import project.workable.movieRama.domain.Movie;

import java.util.Comparator;

/**
 * Comparator for hates field of {@link Movie} class
 */
public class HateComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getHates().size(), o2.getHates().size());
    }

}
