package com.blues.popular_movie_stage1.movie_list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blues.popular_movie_stage1.detail_screen.DetailActivity;
import com.blues.popular_movie_stage1.R;
import com.blues.popular_movie_stage1.SettingsActivity;
import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MovieFragement extends Fragment implements MovieListActivityMVP.View, MovieAdapter.ClickMovieHandler, SharedPreferences.OnSharedPreferenceChangeListener{

    private final String LOG_TAG = MovieFragement.class.getSimpleName();

    @Inject
    MovieListActivityMVP.Presenter presenter;

    private String mOrder;

    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public MovieFragement() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mOrder = pref.getString(getString(R.string.pref_units_key),getString(R.string.pref_units_popular));

        presenter.setView(this);
        presenter.getMovies(mOrder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_main,container, false);


        //init recycler view
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());
        movieAdapter.setHandler(this);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(movieAdapter);

        return rootView;
    }

    @Override
    public void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void jumpToDetailActivity(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void jumpToSettingActivity() {
        Intent settingIntent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(settingIntent);
    }

    @Override
    public void updateData(List<Movie> movies) {
        movieAdapter.clear();
        movieAdapter.updateMovies(movies);
    }

    @Override
    public void showErrorMessage(String msg) {
        Log.v("test", "error message");
    }

    @Override
    public void clickMovie(Movie movie) {
        presenter.listItemClicked(movie);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        String order = sharedPreferences.getString(getString(R.string.pref_units_key),getString(R.string.pref_units_popular));
        presenter.getMovies(order);
    }
}
