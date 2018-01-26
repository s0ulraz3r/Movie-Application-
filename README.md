# Movie-Application-Android App


# User Interface
## The main activity contains two tabs:
1. Most Recent
2. Highest Rated
* Each tab will show a ListView of the movies you’ve entered, sorted by date entered (“Most Recent”) or by rating (“Highest Rated”).
### Each list view item will show:
1. The name of the movie
2. The date it was entered
3. A small RatingBar of 1-5 stars
# Note: 
> For a small RatingBar, used style="?android:attr/ratingBarStyleSmall" in the layout.
* Action bar contains icon of the main activity which provides us to add a new movie.

### A second Add/Edit movie activity will show:
1. The name of the movie
2. A DatePicker
3. A RatingBar of 1-5 stars
4. A checkbox labeled “Ask Me Later”

* Save and Cancel icons on the action bar of the Add/Edit activity provides user to save or cancel the changes.

##Operation
* Switching to a new tab will show the list of movies sorted by the selected criteria.
* Clicking the “add” icon will start the Add/Edit activity to add a new movie.
* Clicking an item in a ListView will start the Add/Edit activity to edit that movie’s details.
* If the “Ask Me Later” button is checked when a movie is saved, a notification reminding the user to rate the movie will be sent to the user. The notification will have a PendingIntent for the Add/Edit activity.

##Specifications
* Movies will be saved to a SQLite database. 
# Note: 
> “Ask Me Later” does not need to be saved.


