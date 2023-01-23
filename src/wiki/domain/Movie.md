## Description
Object that represents the movies in MovieRama

## Fields
* Long id - *not null*
* String title - *title of the movie*
* String description - *description of the movie*
* Date publicationDate - *date that movie was submitted*
* [MUser](MUser.md) user - *user that submitted the movie*
* List of [Vote](Vote.md) votes - *votes of the movie*