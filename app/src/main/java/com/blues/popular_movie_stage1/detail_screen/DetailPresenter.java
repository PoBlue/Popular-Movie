package com.blues.popular_movie_stage1.detail_screen;

import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Reviews;
import com.blues.popular_movie_stage1.model.Trailer;
import com.blues.popular_movie_stage1.model.Trailers;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wicher on 2017/8/30.
 */

public class DetailPresenter implements DetailActivityMVP.Presenter {
    private DetailActivityMVP.View mView;
    private DetailActivityMVP.Model mModel;

    public DetailPresenter(DetailActivityMVP.Model mModel) {
        this.mModel = mModel;
    }


    @Override
    public boolean favourBtnClicked(long movieId) {
        return mModel.markAsFavour(movieId);
    }

    @Override
    public void trailerBtnClicked() {
        Trailer trailer = mModel.getTrailer();
        if (trailer != null){
            mView.openTrailer(trailer);
        } else {
            mView.showErrorMessage("have not trailer yet");
        }
    }

    @Override
    public void trailerClicked(Trailer trailer) {
        if (trailer != null){
            mView.openTrailer(trailer);
        } else {
            mView.showErrorMessage("trailer error yet");
        }
    }

    @Override
    public void reviewClicked(Review review) {
        if (review != null) {
            mView.openReview(review);
        } else {
            mView.showErrorMessage("review open error");
        }
    }

    @Override
    public void getMovieInfo(Movie movie) {
        mView.showMovieInfo(movie);

        mModel.getTrailers(movie.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Trailers>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Trailers trailers) {
                mView.showTrailerInfo(trailers.getTrailers());
            }
        });

        mModel.getReviews(movie.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Reviews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Reviews reviews) {
                        mView.showReviewInfo(reviews.getReviews());
                    }
                });

    }

    @Override
    public void setView(DetailActivityMVP.View view) {
        mView = view;
    }
}
