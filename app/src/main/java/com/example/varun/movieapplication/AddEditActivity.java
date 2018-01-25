package com.example.varun.movieapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddEditActivity extends AppCompatActivity {
    private EditText editText_moviename;
    private RatingBar ratingBar;
    private DatePicker datePicker;
    private DBHelper mDB;
    private MovieDataModel movieDataModel;
    private CheckBox askMeLater;
    boolean editFlag = false;
    private int ID;
    private NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        editText_moviename =(EditText) findViewById(R.id.editText_movieName);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar_movie);
        datePicker = (DatePicker) findViewById(R.id.datePicker_movie);
        askMeLater = (CheckBox) findViewById(R.id.checkBox_askmelater);
        mDB = new DBHelper(this);
        movieDataModel = new MovieDataModel();
        editMovieItem();
        if(getIntent().getAction()!=null && getIntent().getAction().equals(getString(R.string.movie_intent))){
           Uri uri = getIntent().getData();
            ID=Integer.parseInt(uri.getPathSegments().get(1));
            ArrayList<MovieDataModel> m  = mDB.getRecord(String.valueOf(ID));

            try {
                editText_moviename.setText(m.get(0).getMovieName());
                String[] date = new String[]{m.get(0).getMovieDate().replace("-", ",")};
                String[] arrayLists = date[0].split(",");
                int day = Integer.parseInt(arrayLists[1]);
                int month = Integer.parseInt(arrayLists[0]);
                int year = Integer.parseInt(arrayLists[2]);
                datePicker.updateDate(year,month,day);
                ratingBar.setRating(Float.parseFloat(m.get(0).getMovieRating()));
            }catch (Exception e){}

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                savetodb();
                break;
            case R.id.cancel:
                Intent intent = new Intent(this,TabbedActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void savetodb(){
        String moviename = editText_moviename.getText().toString();
        String ratingValue = String.valueOf(ratingBar.getRating());
        String Day = String.valueOf(datePicker.getDayOfMonth());
        String Month = String.valueOf(datePicker.getMonth()+1);
        String Year = String.valueOf(datePicker.getYear());
        if (askMeLater.isChecked() == true){            //TODO USE EXPLICIT INTENT IT MUST HAVE MOVIE ID AS EXTRA OR CONTENT PROVIDER TO START ACTIVITY
            if (ratingValue == "0.0"){

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
                Uri rate=Uri.parse("rating://movieapp.com/movie/"+ID);
                Intent intent = new Intent(getString(R.string.movie_intent),rate);
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent.putExtra("_ID",ID);
                //startActivity(intent);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentTitle("MovieRating");
                builder.setContentText("Rate the movie.");
                builder.setSubText("Tap to view the app.");
                builder.setAutoCancel(true);
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Will display the notification in the notification bar
                notificationManager.notify(1, builder.build());

            }
        }
        StringBuilder dateValue = new StringBuilder();
        dateValue.append(Month).append("-").append(Day).append("-").append(Year);
        Log.d("Name",moviename);
        Log.d("rating",ratingValue);
        Log.d("date", String.valueOf(dateValue));
        if (moviename == null || moviename.equals("")){
            Toast.makeText(this,"Enter Movie Name",Toast.LENGTH_SHORT).show();
            return;
        }

        movieDataModel.setMovieName(moviename);
        movieDataModel.setMovieDate(String.valueOf(dateValue));
        movieDataModel.setMovieRating(ratingValue);
        if (editFlag == false) {
            mDB.insertRecord(movieDataModel);
        }else {
            movieDataModel.setID(String.valueOf(ID));
            mDB.updateRecord(movieDataModel);
           // notificationManager.cancel(1);
        }
        Intent intent = new Intent(this,TabbedActivity.class);
        startActivity(intent);
    }

    public void editMovieItem(){
        try {
            Intent intent = getIntent();
            ID = Integer.parseInt(intent.getStringExtra("_ID"));
            String[] date = new String[]{intent.getStringExtra("MovieDate").replace("-", ",")};
            String[] arrayLists = date[0].split(",");
            int day = Integer.parseInt(arrayLists[1]);
            int month = Integer.parseInt(arrayLists[0]);
            int year = Integer.parseInt(arrayLists[2]);
            editText_moviename.setText(intent.getStringExtra("MovieName"));
            ratingBar.setRating(Float.parseFloat(intent.getStringExtra("MovieRating")));
            datePicker.updateDate(year, month, day);
            editFlag = true;
        }catch (Exception e){}
    }




    }



