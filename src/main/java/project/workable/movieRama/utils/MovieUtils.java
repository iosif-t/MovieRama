package project.workable.movieRama.utils;

import project.workable.movieRama.domain.Movie;
import project.workable.movieRama.domain.Vote;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

/**
 * Utility class for {@link Movie}
 */
public class MovieUtils {

    /**
     * private constructor
     */
    private MovieUtils() {
        // Empty constructor
    }

    /**
     * Calculates the offset between now and publication date
     * @param publicationDate
     * @return Returns the offset
     */
    public static String getDateOffset(Date publicationDate) {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = publicationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        long months = ChronoUnit.MONTHS.between(from , to);
        long weeks = ChronoUnit.WEEKS.between(from , to);
        long days = ChronoUnit.DAYS.between(from , to);
        long hours = ChronoUnit.HOURS.between(from , to);
        long minutes = ChronoUnit.MINUTES.between(from , to);
        long seconds = ChronoUnit.SECONDS.between(from , to);

        if (months > 0) return "" + months + " months ago";
        if (weeks > 0) return "" + weeks + " weeks ago";
        if (days > 0) return "" + days + " days ago";
        if (hours > 0) return "" + hours + " hours ago";
        if (minutes > 0) return "" + minutes + " minutes ago";

        return "" + seconds + " seconds ago";
    }

    /**
     * Checks if user has liked the given movie
     * @param movie
     * @param username
     * @return Returns true if user likes the movie or false otherwise
     */
    public static boolean hasLiked(Movie movie , String username) {
        Optional<Vote> user = movie.getLikes().stream().filter((like) -> like.getUsername().equals(username)).findAny();
        return user.isPresent();
    }

    /**
     * Checks if user has hated the current movie
     * @param movie
     * @param username
     * @return Returns true if user hates the movie or false otherwise
     */
    public static boolean hasHated(Movie movie , String username) {
        Optional<Vote> user = movie.getHates().stream().filter((hate) -> hate.getUsername().equals(username)).findAny();
        return user.isPresent();
    }

}
