package com.example.android.androidapp.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.androidapp.R;
import com.example.android.androidapp.adapter.TrailerAdapter;
import com.example.android.androidapp.generator.MovieAPIGenerator;
import com.example.android.androidapp.interfaces.MovieApi;
import com.example.android.androidapp.interfaces.OnItemClickListener;
import com.example.android.androidapp.models.Results;
import com.example.android.androidapp.models.ReviewModel;
import com.example.android.androidapp.models.TrailerResults;
import com.example.android.androidapp.models.TrailerViedoModel;
import com.example.android.androidapp.models.TrailersResultList;
import com.example.android.androidapp.utils.InternetUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListenerDetails} interface
 * to handle interaction events.
 * Use the {@link FragmentMovieDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMovieDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.movieImageView)
    ImageView movieImageView;
    @Bind(R.id.titleTextView)
    TextView titleTextView;
    @Bind(R.id.titleLayout)
    FrameLayout titleLayout;
    @Bind(R.id.moviePosterImageView)
    ImageView moviePosterImageView;
    @Bind(R.id.favoriteTrailerLayout)
    FrameLayout favoriteTrailerLayout;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.releaseDateTextView)
    TextView releaseDateTextView;
    @Bind(R.id.languageTitleTextView)
    TextView languageTitleTextView;
    @Bind(R.id.languageTextView)
    TextView languageTextView;
    @Bind(R.id.ratingTitleTextView)
    TextView ratingTitleTextView;
    @Bind(R.id.voteAverageTextView)
    TextView voteAverageTextView;
    @Bind(R.id.movieRatingBar)
    AppCompatRatingBar movieRatingBar;
    @Bind(R.id.synopsisTitleTextView)
    TextView synopsisTitleTextView;
    @Bind(R.id.synopsisTextView)
    TextView synopsisTextView;
    @Bind(R.id.synopsisDividerView)
    View synopsisDividerView;
    @Bind(R.id.trailerTitleTextView)
    TextView trailerTitleTextView;
    @Bind(R.id.movieTrailerRecyclerView)
    RecyclerView mMovieTrailerRecyclerView;
    @Bind(R.id.reviewTitleTextView)
    TextView reviewTitleTextView;
    @Bind(R.id.noReviewsTextView)
    TextView noReviewsTextView;
    @Bind(R.id.reviewsLayout)
    LinearLayout reviewsLayout;
    @Bind(R.id.favoriteMovieButton)
    FloatingActionButton favoriteMovieButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String MOVIE_MODEL = "title";

    private OnFragmentInteractionListenerDetails mListener;
    public Results results;
    MovieApi movieApi;
    ArrayList<TrailersResultList> trailerList;
    TrailerAdapter mTrailerAdapter;
    ArrayList<TrailersResultList> tList;

    public FragmentMovieDetail() {
        // Required empty public constructor
    }
    public static FragmentMovieDetail newInstance(String param1, String param2) {
        FragmentMovieDetail fragment = new FragmentMovieDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private static final String TAG = FragmentMovieDetail.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (savedInstanceState != null) {
            results = savedInstanceState.getParcelable("MOVIE_MODEL");
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(MOVIE_MODEL, results);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ButterKnife.bind(this, rootView);
        trailerList = new ArrayList<>();
        tList = new ArrayList<>();
        movieApi = MovieAPIGenerator.createService(MovieApi.class);
        results = new Results();

        if (savedInstanceState != null) {
            results = savedInstanceState.getParcelable("MOVIE_MODEL");
        } else {
            Bundle bundle = getArguments();
            if (bundle != null) {
                results = bundle.getParcelable("OnclickDetails");
                Log.d("Results", "" + results.toString());
            }

        }
        setUPView();
        getReviews();
        getMoviesTrailers();
        Log.d("Size", "" + trailerList.size());



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
        if (context instanceof OnFragmentInteractionListenerDetails) {
            mListener = (OnFragmentInteractionListenerDetails) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface OnFragmentInteractionListenerDetails {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void setUPView() {
        if (results != null) {
            Log.d("Inside the setUPView", "" + results.toString() + " : " + results.getBackdrop_path());
            Picasso.with(getActivity()).load(MovieAPIGenerator.IMAGE_URL + results.getBackdrop_path()).into(movieImageView);
            Picasso.with(getActivity()).load(MovieAPIGenerator.IMAGE_URL + results.getPoster_path()).into(moviePosterImageView);
            titleTextView.setText(results.getTitle());
            releaseDateTextView.setText(results.getRelease_date());
            languageTitleTextView.setText(results.getOriginal_language());
            synopsisTextView.setText(results.getOverview());
            movieRatingBar.setRating(Float.parseFloat(results.getVote_average()));
        }
    }

    public void getMoviesTrailers() {
        if (!InternetUtils.isConnected(getActivity())) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.no_internet, Snackbar.LENGTH_SHORT).show();
            return;
        } else {
            mMovieTrailerRecyclerView.setVisibility(View.VISIBLE);
           // mNoReviewsTextView.setVisibility(View.GONE);
           // mNoTrailersTextView.setVisibility(View.GONE);
        }
        movieApi.fetchTrailers(results.getId(), new Callback<TrailerViedoModel>() {
            @Override
            public void success(TrailerViedoModel trailerResults, Response response) {
                Log.d("TrailerResults : ", "" + trailerResults.toString());
                trailerList.addAll(trailerResults.getResults());
                getTrailers(trailerResults);
                mTrailerAdapter.notifyDataSetChanged();
                //Log.d("List : ", "" + trailerList.toString());

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                Log.d("RetrofitError : ", "" + error.getMessage());
            }
        });


    }

    //http://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent
    public void youtubeVideo(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(intent);
        }
    }

    public void getTrailers(TrailerViedoModel trailerViedoModel){
        tList = trailerViedoModel.getResults();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mMovieTrailerRecyclerView.setLayoutManager(linearLayoutManager);
        mMovieTrailerRecyclerView.setHasFixedSize(true);

        if (mTrailerAdapter == null) {
            mTrailerAdapter = new TrailerAdapter(getActivity(), tList, new OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int itemPosition) {
                    youtubeVideo(tList.get(itemPosition).getKey());
                }
            });

        }
        mTrailerAdapter.notifyDataSetChanged();
        mMovieTrailerRecyclerView.setAdapter(mTrailerAdapter);

    }

    public void getReviews(){
        if(!InternetUtils.isConnected(getActivity())){
            Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.no_internet, Snackbar.LENGTH_SHORT).show();
            return;
        } else {

            movieApi.getReviews(results.getId(), new Callback<ReviewModel>() {
                @Override
                public void success(ReviewModel reviewModel, Response response) {
                    Log.d("ReviewModel : ", ""+reviewModel.toString());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("RetrofitError : ", ""+error.getMessage());
                }
            });

        }
    }
}
