package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.api.FetchMoviesTask;
import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Trailer;

import java.util.List;

/**
 * Created by wicher on 2017/8/29.
 */

public class MovieModel implements MovieListActivityMVP.Model, FetchMoviesTask.Listener {
    private MovieListActivityMVP.Presenter mPresenter;

    @Override
    public void setPresenter(MovieListActivityMVP.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getMovies(String sortOf) {
        new FetchMoviesTask(sortOf, this).execute();
    }

    @Override
    public List<Review> getReviews() {
        return null;
    }

    @Override
    public List<Trailer> getTrailers() {
        return null;
    }

    @Override
    public boolean markAsFavour(boolean isFavour) {
        return false;
    }

    @Override
    public void onMoviesFetchFinished(List<Movie> movies) {
        mPresenter.updateMovies(movies);
    }
}
