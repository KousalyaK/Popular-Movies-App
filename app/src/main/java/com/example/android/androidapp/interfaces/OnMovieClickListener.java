package com.example.android.androidapp.interfaces;

import com.example.android.androidapp.models.MovieModel;
import com.example.android.androidapp.models.Results;

/**
 * Created by anjana on 6/2/16.
 */
public interface OnMovieClickListener {
    void onMovieClick( Results movieResult, boolean isFavorite );
    void loadDefaultMovie(Results movieResult, boolean isFavorite );
    void storeFragmentParams(String filterString, int scrollPosition);
    void storeMovies( MovieModel movieModel );
    void storeFavorites( MovieModel favoriteMovieModel );
}
