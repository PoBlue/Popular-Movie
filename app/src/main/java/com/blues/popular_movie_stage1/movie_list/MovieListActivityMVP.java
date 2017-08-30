package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Movies;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Trailer;

import java.util.List;

import rx.Observable;

/**
 * Created by wicher on 2017/8/29.
 */

public interface MovieListActivityMVP {
    interface View {
        void jumpToDetailActivity(Movie movie);
        void jumpToSettingActivity();
        void updateData(List<Movie> movies);
        void showErrorMessage(String msg);
    }

    interface Presenter {
        void listItemClicked(Movie movie);
        void getMovies(String sortOf);
        void updateMovies(List<Movie> movies);
        void settingIsClicked();
        void setView(View view);
    }

    interface Model {
        Observable<Movies> getMovies(String sortOf);
        List<Review> getReviews();
        List<Trailer> getTrailers();
        boolean markAsFavour(boolean isFavour);
    }
}
