package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.BuildConfig;
import com.blues.popular_movie_stage1.api.MovieDatabaseService;
import com.blues.popular_movie_stage1.detail_screen.DetailActivityMVP;
import com.blues.popular_movie_stage1.model.Movies;
import com.blues.popular_movie_stage1.model.Reviews;
import com.blues.popular_movie_stage1.model.Trailer;
import com.blues.popular_movie_stage1.model.Trailers;

import rx.Observable;

/**
 * Created by wicher on 2017/8/29.
 */

public class MovieModel implements MovieListActivityMVP.Model, DetailActivityMVP.Model {
    private MovieDatabaseService movieDatabaseService;

    public MovieModel(MovieDatabaseService movieDatabaseService) {
        this.movieDatabaseService = movieDatabaseService;
    }

    @Override
    public Observable<Movies> getMovies(String sortOf) {
        return movieDatabaseService.discoverMovies(sortOf, BuildConfig.THE_MOVIE_DATABASE_API_KEY);
    }

    @Override
    public Observable<Reviews> getReviews(long movieId) {
        return movieDatabaseService.findReviewsById(movieId, BuildConfig.THE_MOVIE_DATABASE_API_KEY);
    }

    @Override
    public Observable<Trailers> getTrailers(long movieId) {
        return movieDatabaseService.findTrailersById(movieId, BuildConfig.THE_MOVIE_DATABASE_API_KEY);
    }

    @Override
    public Trailer getTrailer() {
        return null;
    }

    @Override
    public boolean markAsFavour(long movieId) {
        return false;
    }

}
