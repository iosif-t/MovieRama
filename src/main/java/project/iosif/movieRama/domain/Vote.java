package project.iosif.movieRama.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity class for votes
 */
@Entity
public class Vote {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * movie
     */
    @ManyToOne
    private Movie movie;

    /**
     * username
     */
    private String username;

    /**
     * vote type
     */
    private VoteType type;

    /**
     * Constructor
     */
    public Vote() {
        // Empty constructor
    }

    /**
     * Constructor
     * @param username
     * @param type
     */
    public Vote(String username , VoteType type) {
        this.username = username;
        this.type = type;
    }

    /**
     * Getter for id
     * @return Returns the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for movie
     * @return Returns the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Setter for movie
     * @param movie to set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Getter for username
     * @return Returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for type
     * @param type to set
     */
    public void setType(VoteType type) {
        this.type = type;
    }

    /**
     * Getter for type
     * @return Returns the type
     */
    public VoteType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote like = (Vote) o;
        return Objects.equals(id, like.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
