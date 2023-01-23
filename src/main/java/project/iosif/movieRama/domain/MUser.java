package project.iosif.movieRama.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity class for User
 */
@Entity
public class MUser {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    @OneToMany
    @JoinColumn(name = "muser_id")
    private Set<Movie> movies = new HashSet<>();

    /**
     * Constructor
     */
    public MUser() {
        // Empty constructor
    }

    /**
     * Constructor
     * @param userName
     * @param password
     */
    public MUser(String userName , String password) {
        this.username = userName;
        this.password = password;
    }

    /**
     * Getter for password
     * @return Returns the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for username
     * @return Returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for id
     * @param username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Getter for movies
     * @return Returns the movies
     */
    public Set<Movie> getMovies() {
        return movies;
    }

    /**
     * Setter for movies
     * @param movies to set
     */
    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", movies=" + movies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MUser user = (MUser) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
