package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.BuildConfig;
import com.blues.popular_movie_stage1.api.MovieDatabaseService;
import com.blues.popular_movie_stage1.model.Movies;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Trailer;

import java.util.List;

import rx.Observable;

/**
 * Created by wicher on 2017/8/29.
 */

public class MovieModel implements MovieListActivityMVP.Model {
    private MovieDatabaseService movieDatabaseService;

    public MovieModel(MovieDatabaseService movieDatabaseService) {
        this.movieDatabaseService = movieDatabaseService;
    }

    @Override
    public Observable<Movies> getMovies(String sortOf) {
        return movieDatabaseService.discoverMovies(sortOf, BuildConfig.THE_MOVIE_DATABASE_API_KEY);
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

}
