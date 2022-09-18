package project.workable.movieRama.comparators;

import project.workable.movieRama.domain.Movie;

import java.util.Comparator;

/**
 * Comparator for publicationDate field of {@link Movie} class
 */
public class DateComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getPublicationDate().compareTo(o2.getPublicationDate());
    }

}
