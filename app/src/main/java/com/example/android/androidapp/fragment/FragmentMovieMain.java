package com.example.android.androidapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.example.android.androidapp.R;
import com.example.android.androidapp.adapter.MovieGridAdapter;
import com.example.android.androidapp.generator.MovieAPIGenerator;
import com.example.android.androidapp.interfaces.MovieApi;
import com.example.android.androidapp.interfaces.OnItemClickListener;
import com.example.android.androidapp.interfaces.OnMovieClickListener;
import com.example.android.androidapp.models.MovieModel;
import com.example.android.androidapp.models.Results;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentMovieMain extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Bind views
    @Bind(R.id.gridLayout)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    // Helper
    private OnFragmentInteractionListener mListener;
    private static final String TAG = FragmentMovieMain.class.getSimpleName();
    MovieApi movieApi;
    ArrayList<Results> modelsList;
    MovieGridAdapter movieGridAdapter;
    private OnMovieClickListener onMovieClickListener;
    MovieModel mMovieModel;


    public FragmentMovieMain() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentMovieMain newInstance(String param1, String param2) {
        FragmentMovieMain fragment = new FragmentMovieMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_main, container, false);

        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        movieApi = MovieAPIGenerator.createService(MovieApi.class);
        modelsList = new ArrayList<>();
        mMovieModel = new MovieModel();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.gridItem));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        callApi();

        movieGridAdapter = new MovieGridAdapter(getActivity(), modelsList, new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int itemPosition) {
                Results results = movieGridAdapter.getItem(itemPosition);

                if( modelsList != null ) {
                    onMovieClickListener.onMovieClick(results,true);
                    //onMovieClickListener.onMovieClick(modelsList, mFavoriteMoviesList.contains(movieResult));
                }
                else {
                   // onMovieClickListener.onMovieClick(modelsList, (mFilterString != null && mFilterString.equals(FILTER_FAVORITE)));
                }

            }
        });
        mRecyclerView.setAdapter(movieGridAdapter);



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if (context instanceof OnMovieClickListener) {
            onMovieClickListener = (OnMovieClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMovieClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void callApi(){

        movieApi.getMovies("The Hateful Eight", new Callback<MovieModel>() {
            @Override
            public void success(MovieModel movieModel, Response response) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("Response", movieModel.toString());
                mMovieModel = movieModel;
                onMovieClickListener.storeMovies(movieModel);
                modelsList.addAll(movieModel.getResults());
                movieGridAdapter.notifyDataSetChanged();

                Log.d("ModelListSize", ""+ modelsList.size());
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.INVISIBLE);
                error.printStackTrace();
                Log.d("RetrofitError", error.getMessage());

            }
        });
    }

//    private void shareTrailer() {
//        if( mTrailerResultList != null && mTrailerResultList.size() > 0 ) {
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT,
//                    "Movie Night :\n"
//                            + mMovieResult.getOriginalTitle()
//                            +" Trailer : \n"
//                            + "http://www.youtube.com/watch?v=" + mTrailerResultList.get(0).getKey());
//            sendIntent.setType("text/plain");
//            startActivity(sendIntent);
//        }
//        else {
//            Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.no_trailers_to_share, Snackbar.LENGTH_SHORT).show();
//        }
//
//    }


}
