package com.example.varun.movieapplication;

/**
 * Created by varun on 12/3/2017.
 */

public class MovieDataModel {

    private String _Id;
    private String name;
    private String date;
    private String rating;

    public String getID() {
        return _Id;
    }
    public void setID(String ID) {
        this._Id = ID;
    }
    public String getMovieName() {
        return name;
    }
    public String getMovieDate() {
        return date;
    }

    public String getMovieRating() {
        return rating;
    }

    public void setMovieName(String name) {
        this.name = name;
    }
    public void setMovieDate(String date) {
        this.date = date;
    }
    public void setMovieRating(String rating) {
        this.rating = rating;
    }


}







