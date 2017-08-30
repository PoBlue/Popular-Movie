package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Movies;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wicher on 2017/8/29.
 */

public class MoviePresenter implements MovieListActivityMVP.Presenter{
    private MovieListActivityMVP.Model mModel;
    private MovieListActivityMVP.View mView;

    public MoviePresenter(MovieListActivityMVP.Model model) {
        mModel = model;
    }

    @Override
    public void listItemClicked(Movie movie) {
        mView.jumpToDetailActivity(movie);
    }

    @Override
    public void getMovies(String sortOf) {
        mModel.getMovies(sortOf)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movies>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movies movies) {
                        if(movies != null){
                            mView.updateData(movies.getMovies());
                        } else {
                            mView.showErrorMessage("No Movies");
                        }
                    }
                });
    }

    @Override
    public void updateMovies(List<Movie> movies) {
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
