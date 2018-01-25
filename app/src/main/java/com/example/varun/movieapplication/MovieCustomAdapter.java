package com.example.varun.movieapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.varun.movieapplication.R.id.parent;

/**
 * Created by varun on 12/4/2017.
 */

public class MovieCustomAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<MovieDataModel> movieDataModelList;

    public MovieCustomAdapter(Context context, int listview_movie, ArrayList<MovieDataModel> movieDataModelList) {
        super(context,listview_movie,movieDataModelList);
        this.context = context;
        this.movieDataModelList = movieDataModelList;
    }

    @Override
    public int getCount() {
        return movieDataModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieDataModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = View.inflate(context,R.layout.listview_movie,null);
        TextView tvName = (TextView) view1.findViewById(R.id.textView_movieName);
        tvName.setText(movieDataModelList.get(i).getMovieName());
        TextView tvDate = (TextView) view1.findViewById(R.id.textView_Date);
        tvDate.setText(movieDataModelList.get(i).getMovieDate());
        RatingBar rbRating = (RatingBar) view1.findViewById(R.id.ratingBar_movie);
        rbRating.setRating(Float.parseFloat(movieDataModelList.get(i).getMovieRating()));
        view1.setTag(movieDataModelList.get(i).getID());
        return view1;
    }


}
