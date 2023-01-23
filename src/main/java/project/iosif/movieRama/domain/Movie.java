package project.iosif.movieRama.domain;

import project.iosif.movieRama.utils.MovieUtils;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity class for Movies
 */
@Entity
public class Movie {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * title
     */
    private String title;

    /**
     * description
     */
    private String description;

    /**
     * user
     */
    @ManyToOne
    private MUser user;

    /**
     * publication date
     */
    private Date publicationDate;

    /**
     * votes
     */
    @OneToMany
    @JoinColumn(name = "movie_id")
    private Set<Vote> votes = new HashSet<>();

    /**
     * Constructor
     */
    public Movie() {
        // Empty constructor
    }

    /**
     * Constructor
     * @param title
     * @param description
     * @param publicationDate
     */
    public Movie(String title, String description, Date publicationDate) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
    }

    /**
     * Getter for title
     * @return Returns the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for decription
     * @return Returns the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for user
     * @return Returns the user
     */
    public MUser getUser() {
        return user;
    }

    /**
     * Setter for user
     * @param user to set
     */
    public void setUser(MUser user) {
        this.user = user;
    }

    /**
     * Getter for publicationDate
     * @return Returns the publicationDate
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Setter for publicationDate
     * @param publicationDate to set
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * @return Returns the offset between now and publicationDate of movie
     */
    public String getDateOffset() {
        return MovieUtils.getDateOffset(publicationDate);
    }

    /**
     * Gets the likes of movie
     * @return Returns the likes
     */
    public List<Vote> getLikes() {
        return votes.stream().filter((vote) -> vote.getType().equals(VoteType.LIKE)).collect(Collectors.toList());
    }

    /**
     * Gets the hates of movies
     * @return Returns the hates
     */
    public List<Vote> getHates() {
        return votes.stream().filter((vote) -> vote.getType().equals(VoteType.HATE)).collect(Collectors.toList());
    }

    /**
     * Getter for votes
     * @return Returns the votes
     */
    public Set<Vote> getVotes() {
        return votes;
    }

    /**
     * Setter for votes
     * @param votes to set
     */
    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    /**
     * Setter for id
     * @param id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return Returns the id
     */
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
