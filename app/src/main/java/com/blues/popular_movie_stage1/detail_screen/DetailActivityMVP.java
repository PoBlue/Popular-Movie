package com.blues.popular_movie_stage1.detail_screen;

import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Movies;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Reviews;
import com.blues.popular_movie_stage1.model.Trailer;
import com.blues.popular_movie_stage1.model.Trailers;

import java.util.List;

import rx.Observable;

/**
 * Created by wicher on 2017/8/30.
 */

public interface DetailActivityMVP {
    interface View {
        void showMovieInfo(Movie movie);
        void showReviewInfo(List<Review> reviews);
        void showTrailerInfo(List<Trailer> trailers);
        void openTrailer(Trailer trailer);
        void openReview(Review review);
        void showErrorMessage(String msg);
    }

    interface Presenter {
        boolean favourBtnClicked(long movieId);
        void trailerBtnClicked();
        void trailerClicked(Trailer trailer);
        void reviewClicked(Review review);
        void getMovieInfo(Movie movie);
        void setView(DetailActivityMVP.View view);
    }

    interface Model {
        Observable<Movies> getMovies(String sortOf);
        Observable<Reviews> getReviews(long movieId);
        Observable<Trailers> getTrailers(long movieId);
        Trailer getTrailer();
        boolean markAsFavour(long movieId);
    }
}
