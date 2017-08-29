package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.model.Movie;

import java.util.List;

/**
 * Created by wicher on 2017/8/29.
 */

public class MoviePresenter implements MovieListActivityMVP.Presenter{
    private MovieListActivityMVP.Model mModel;
    private MovieListActivityMVP.View mView;

    public MoviePresenter() {
        mModel = new MovieModel();
        mModel.setPresenter(this);
    }

    @Override
    public void listItemClicked(Movie movie) {
        mView.jumpToDetailActivity(movie);
    }

    @Override
    public void getMovies(String sortOf) {
        mModel.getMovies(sortOf);
    }

    @Override
    public void updateMovies(List<Movie> movies) {
        if(movies != null){
            mView.updateData(movies);
        } else {
            mView.showErrorMessage("No Movies");
        }
    }

    @Override
    public void settingIsClicked() {
        mView.jumpToSettingActivity();
    }

    @Override
    public void setView(MovieListActivityMVP.View view) {
        mView = view;
    }
}
