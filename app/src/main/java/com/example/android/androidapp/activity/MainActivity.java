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
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    setTitle(savedInstanceState.getString(MOVIE_TITLE, getResources().getString(R.string.app_name)));
                    loadFragmentGridMain();
                }
            }
        }

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.app_name);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            if( mTowPane ) {
                super.onBackPressed();
            }
            else {
                if( !getTitle().toString().equals(getResources().getString(R.string.app_name)) ) {
                    loadFragmentGridMain();
                }
                else {
                    super.onBackPressed();
                }
            }


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

        if( !mTowPane ) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(movieResult.getTitle());
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mTowPane = false;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                mTowPane = true;
        }
    }

    @Override
    public void loadDefaultMovie(Results movieResult, boolean isFavorite) {
        if( mTowPane ) {
            Bundle bundle = new Bundle();
            bundle.putParcelable( MovieModel.class.getSimpleName(), movieResult );
            bundle.putBoolean("IS_FAV", isFavorite);
            mFragment = new FragmentMovieDetail();
            mFragment.setArguments( bundle );
            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.moviesDetailFrameLayout, mFragment,  FragmentMovieDetail.class.getSimpleName());
            mFragmentTransaction.commit();
        }


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
