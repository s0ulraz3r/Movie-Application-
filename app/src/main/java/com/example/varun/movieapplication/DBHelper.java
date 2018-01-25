package com.example.varun.movieapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by varun on 12/3/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MovieList.db";
    public static final String TABLE_NAME = "movie";
    public static final String _ID = "id";
    public static final String MOVIE_NAME = "movie_name";
    public static final String MOVIE_DATE = "movie_date";
    public static final String MOVIE_RATING = "movie_rating";


    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        "("+_ID +" integer primary key autoincrement, "+ MOVIE_NAME +" text, "+ MOVIE_DATE +" text,"+MOVIE_RATING+ " text)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
        throw new SQLiteException("Can't downgrade database from version " +
                i + " to " + i1);
    }


    public void insertRecord(MovieDataModel movieDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, movieDataModel.getMovieName());
        contentValues.put(MOVIE_DATE, movieDataModel.getMovieDate());
        contentValues.put(MOVIE_RATING,movieDataModel.getMovieRating());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateRecord(MovieDataModel movieDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, movieDataModel.getMovieName());
        contentValues.put(MOVIE_DATE, movieDataModel.getMovieDate());
        contentValues.put(MOVIE_RATING,movieDataModel.getMovieRating());
        db.update(TABLE_NAME, contentValues, _ID + " = ?", new String[]{String.valueOf(movieDataModel.getID())});
        db.close();
    }

    public void deleteRecord(MovieDataModel movieDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + " = ?", new String[]{String.valueOf(movieDataModel.getID())});
        db.close();
    }


    public ArrayList<MovieDataModel> getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, MOVIE_DATE+ " DESC");
        ArrayList<MovieDataModel> movies = new ArrayList<MovieDataModel>();
        MovieDataModel movieDataModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                movieDataModel = new MovieDataModel();
                movieDataModel.setID(cursor.getString(0));
                movieDataModel.setMovieName(cursor.getString(1));
                movieDataModel.setMovieDate(cursor.getString(2));
                movieDataModel.setMovieRating(cursor.getString(3));
                movies.add(movieDataModel);
            }
        }
        cursor.close();
        db.close();
        return movies;
    }

    public ArrayList<MovieDataModel> getAllRecords_HighestRating() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, MOVIE_RATING+ " DESC");
        ArrayList<MovieDataModel> movies = new ArrayList<MovieDataModel>();
        MovieDataModel movieDataModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                movieDataModel = new MovieDataModel();
                movieDataModel.setID(cursor.getString(0));
                movieDataModel.setMovieName(cursor.getString(1));
                movieDataModel.setMovieDate(cursor.getString(2));
                movieDataModel.setMovieRating(cursor.getString(3));
                movies.add(movieDataModel);
            }
        }
        cursor.close();
        db.close();
        return movies;
    }

    public ArrayList<MovieDataModel> getRecord(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] arg = {ID};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where "+ _ID + " = "+ID,null);
        ArrayList<MovieDataModel> movies = new ArrayList<MovieDataModel>();
        MovieDataModel movieDataModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                movieDataModel = new MovieDataModel();
                movieDataModel.setID(cursor.getString(0));
                movieDataModel.setMovieName(cursor.getString(1));
                movieDataModel.setMovieDate(cursor.getString(2));
                movieDataModel.setMovieRating(cursor.getString(3));
                movies.add(movieDataModel);
            }
        }
        cursor.close();
        db.close();
        return movies;
    }
}
