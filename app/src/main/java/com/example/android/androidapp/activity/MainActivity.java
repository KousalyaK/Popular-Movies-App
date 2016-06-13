package com.example.android.androidapp.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.androidapp.R;
import com.example.android.androidapp.fragment.FragmentMovieDetail;
import com.example.android.androidapp.fragment.FragmentMovieMain;
import com.example.android.androidapp.interfaces.OnMovieClickListener;
import com.example.android.androidapp.models.MovieModel;
import com.example.android.androidapp.models.Results;

public class MainActivity extends AppCompatActivity implements FragmentMovieMain.OnFragmentInteractionListener, FragmentMovieDetail.OnFragmentInteractionListenerDetails, OnMovieClickListener {


    Boolean mTowPane =false;
    private Fragment mFragment;
    private FragmentTransaction mFragmentTransaction;
    private static final String TWO_MODE = "two_pane_mode";
    private static final String MOVIE_TITLE = "Movie Guide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.moviesDetailFrameLayout) != null){
            mTowPane = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            if(savedInstanceState == null){
                loadFragmentGridMain();
            } else {
                mTowPane = savedInstanceState.getBoolean(TWO_MODE, false);
                if( mTowPane ) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    setTitle(savedInstanceState.getString(MOVIE_TITLE, getResources().getString(R.string.app_name)));

                }


            }
        }


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setTitle("Popular Movie");
//
//        Configuration config = getResources().getConfiguration();
//
//        if(config.orientation == 1){
//            mTowPane = false;
//        } else {
//            mTowPane = true;
//        }
//
//        if(mTowPane){
//            loadTwoPaneFragment();
//        } else  {
//            loadFragmentGridMain();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.favourite) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void loadFragmentGridMain(){

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragment = getSupportFragmentManager().findFragmentByTag(FragmentMovieMain.class.getSimpleName());

        if( mFragment == null ) {
            mFragment = new FragmentMovieMain();
        }

        mFragmentTransaction.replace(R.id.moviesGridFrameLayout, mFragment, FragmentMovieMain.class.getSimpleName());
        if(mTowPane){
            mFragmentTransaction.replace(R.id.moviesDetailFrameLayout, mFragment, FragmentMovieMain.class.getSimpleName());
        }
        mFragmentTransaction.commit();

    }

    public void loadTwoPaneFragment(){

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragment = getSupportFragmentManager().findFragmentByTag(FragmentMovieDetail.class.getSimpleName());
        if( mFragment == null ) {
            mFragment = new FragmentMovieDetail();
        }
        mFragmentTransaction.replace(R.id.moviesDetailFrameLayout, mFragment, FragmentMovieMain.class.getSimpleName());
        mFragmentTransaction.commit();
    }

    @Override
    public void onMovieClick(Results movieResult, boolean isFavorite) {
        Bundle bundle = new Bundle();
        Log.d("onMovieClick", movieResult.getOriginal_title());
        mFragment = new FragmentMovieDetail();
        bundle.putParcelable("OnclickDetails", movieResult);
        bundle.putBoolean("isFav", isFavorite);
        mFragment.setArguments(bundle);
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(mTowPane){
            mFragmentTransaction.replace(R.id.moviesDetailFrameLayout, mFragment, FragmentMovieMain.class.getSimpleName());
        } else {
            mFragmentTransaction.replace(R.id.moviesGridFrameLayout, mFragment, FragmentMovieDetail.class.getSimpleName());
        }
        mFragmentTransaction.commit();

    }

    @Override
    public void loadDefaultMovie(Results movieResult, boolean isFavorite) {

    }

    @Override
    public void storeFragmentParams(String filterString, int scrollPosition) {

    }

    @Override
    public void storeMovies(MovieModel movieModel) {

    }

    @Override
    public void storeFavorites(MovieModel favoriteMovieModel) {

    }
}
