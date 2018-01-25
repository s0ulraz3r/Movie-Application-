package com.example.varun.movieapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by varun on 12/2/2017.
 */

public class HighestRatedActivity extends Fragment {
    ListView listView_HighestRated;
    private DBHelper mDB;
    private MovieCustomAdapter movieCustomAdapter;
    private ArrayList<MovieDataModel> lists;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_highest_rated, container, false);
        listView_HighestRated = (ListView)rootView.findViewById(R.id.listView2);
        lists = mDB.getAllRecords_HighestRating();
        //sortByRating();
        movieCustomAdapter = new MovieCustomAdapter(getActivity(),R.layout.listview_movie,lists);
        listView_HighestRated.setAdapter(movieCustomAdapter);
        listView_HighestRated.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieDataModel itemClicked = (MovieDataModel) adapterView.getItemAtPosition(i);
                Intent itemClickIntent = new Intent(getActivity(),AddEditActivity.class);
                itemClickIntent.putExtra("_ID",itemClicked.getID());
                itemClickIntent.putExtra("MovieName",itemClicked.getMovieName());
                itemClickIntent.putExtra("MovieDate",itemClicked.getMovieDate());
                itemClickIntent.putExtra("MovieRating",itemClicked.getMovieRating());
                startActivity(itemClickIntent);
            }
        });
        return rootView;
    }


}
