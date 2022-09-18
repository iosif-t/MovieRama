## Description
Service layer with operation for movies

## Fields
* MovieRepository movieRepository - *repository of movie*
* UserRepository userRepository - *repository of user*
* Long userId - *id of user in case we want to get the movies of a certain user*

## Methods
* getUserMovies() - *returns the movies that correspond to userId*
* voteHandler(Movie movie, MUser user, VoteType type)
    * checks if movie has been submitted by user
    * if not,passes the movie to another like/hate handler depending on type
    * if yes,ignores
* movieBelongToUser(Movie movie, MUser user) - *checks if movie belongs to user*
* hateHandler(Movie movie, MUser currentUser) - *checks if user has hated the movie*
  * if yes,decreases the hated vote of currentUser to movie
  * if not,increases the hated vote and checks if user has liked the movie
  * if user has liked the movie,decreases the like vote
* likeHandler(Movie movie, MUser currentUser) - *checks if user has liked the movie*
  * if yes,decreases the liked vote of currentUser to movie
  * if not,increases the liked vote and checks if user has hated the movie
  * if user has hated the movie,decreases the hate vote
* increaseVotes(Movie movie, MUser currentUser, VoteType type) - *Increases vote*
* decreaseVotes(Movie movie, MUser currentUser, VoteType type) - *Decreases vote*
* createMovie(Movie movie,MUser user) - *creates new movie for the user*
* findMovieById(Long id) - *Returns movie with the given id*
* getMovies() - *Returns a list with all movies*
* getMoviesSortedByLike() - *Returns a list of movies sorted by like*
* getMoviesSortedByHate() - *Returns a list of movies sorted by hate*
* getMoviesSortedByDate() - *Returns a list of movies sorted by date*
* setUserId(Long id) - *sets userId so movies of user with userId will be returned*
