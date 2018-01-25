package com.example.varun.movieapplication;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TabbedActivity extends AppCompatActivity {
    private ListView listView_movie;
    private ArrayList<MovieDataModel> lists;
    private MovieCustomAdapter movieCustomAdapter;
    private DBHelper mDB;
    private SectionsPagerAdapter sectionsPagerAdapterObj;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mDB = new DBHelper(this);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(this,AddEditActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        @Override
//        public Object instantiateItem(final ViewGroup container, int position) { //TODO
//            super.instantiateItem(container, position);
//            //setContentView(R.layout.activity_tabbed);
//            sectionsPagerAdapterObj = new SectionsPagerAdapter(getSupportFragmentManager());
//            String tabName = String.valueOf(sectionsPagerAdapterObj.getPageTitle(0));
//            if (tabName.equals("Most Recent")){
//                setContentView(R.layout.fragment_tab_most_recent);
//                listView_movie = (ListView)findViewById(R.id.listView1);
//
//                lists = mDB.getAllRecords();
//                //sortByDate();
//                movieCustomAdapter = new MovieCustomAdapter(container.getContext(),R.layout.listview_movie,lists);
//                listView_movie.setAdapter(movieCustomAdapter);
//                Toast.makeText(container.getContext(),"MostRecent",Toast.LENGTH_SHORT).show();
//                sortByDate();
//            }
//            else if(tabName.equals("Highest Rated") ) {
//
//                listView_movie = (ListView)findViewById(R.id.listView2);
//
//                lists = mDB.getAllRecords();
//                //sortByDate();
//                Log.d("flag", String.valueOf(flag));
//                movieCustomAdapter = new MovieCustomAdapter(container.getContext(),R.layout.listview_movie,lists);
//                listView_movie.setAdapter(movieCustomAdapter);
//                Toast.makeText(container.getContext(),"HighestRated",Toast.LENGTH_SHORT).show();
//                sortByRating();
//            }
//            listView_movie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    MovieDataModel itemClicked = (MovieDataModel) adapterView.getItemAtPosition(i);
//                    Intent itemClickIntent = new Intent(getApplicationContext(),AddEditActivity.class);
//                    itemClickIntent.putExtra("_ID",itemClicked.getID());
//                    itemClickIntent.putExtra("MovieName",itemClicked.getMovieName());
//                    itemClickIntent.putExtra("MovieDate",itemClicked.getMovieDate());
//                    itemClickIntent.putExtra("MovieRating",itemClicked.getMovieRating());
//                    startActivity(itemClickIntent);
//                }
//            });
//            return listView_movie;
//        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    MostRecentActivity mostRecentActivity_tab = new MostRecentActivity();
                    return mostRecentActivity_tab;
                case 1:
                    HighestRatedActivity highestRatedActivity_tab = new HighestRatedActivity();
                    return highestRatedActivity_tab;
                default:
                    return null;
            }

        }


        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Most Recent";

                case 1:
                    return "Highest Rated";


            }
            return null;
        }
    }

}
