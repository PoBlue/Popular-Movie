package com.blues.popular_movie_stage1.detail_screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blues.popular_movie_stage1.R;
import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Trailer;
import com.blues.popular_movie_stage1.root.App;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailActivityMVP.View, ReviewListAdapter.Callbacks, TrailerListAdapter.Callbacks {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String ARG_MOVIE = "ARG_MOVIE";
    private Movie mMovie;
    private ReviewListAdapter mReviewListAdapter;
    private TrailerListAdapter mTrailerListAdapter;

    @Inject
    DetailActivityMVP.Presenter presenter;

    @Bind(R.id.detail_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.button_watch_trailer)
    Button watch_trailer_btn;

    @Bind(R.id.movie_reviews)
    RecyclerView movieReviewsRecycler;

    @Bind(R.id.movie_videos)
    RecyclerView movieTrailersRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        //init view
        mReviewListAdapter = new ReviewListAdapter(new ArrayList<Review>(), this);
        movieReviewsRecycler.setAdapter(mReviewListAdapter);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        movieTrailersRecycler.setLayoutManager(layoutManager);
        mTrailerListAdapter = new TrailerListAdapter(new ArrayList<Trailer>(), this);
        movieTrailersRecycler.setAdapter(mTrailerListAdapter);
        movieTrailersRecycler.setNestedScrollingEnabled(false);

        //get data and init presenter
        Intent intent = getIntent();
        mMovie = intent.getParcelableExtra(ARG_MOVIE);
        presenter.setView(this);
        presenter.getMovieInfo(mMovie);

        setSupportActionBar(mToolbar);
    }

    @Override
    public void showMovieInfo(Movie movie) {
        if (movie != null) {
            String title = movie.getTitle();
            String posterUrl = movie.getPosterUrl(this);
            String overview = movie.getOverview();
            String vote = movie.getUserRating();
            String date = movie.getReleaseDate();

            ((TextView) findViewById(R.id.movie_title)).setText(title);
            ((TextView) findViewById(R.id.movie_overview)).setText(overview);
            ((TextView) findViewById(R.id.movie_rating)).setText("VOTE: " + vote);
            ((TextView) findViewById(R.id.movie_release_date)).setText("DATE: " + date);

            Picasso.with(this)
                    .load(posterUrl)
                    .into((ImageView) findViewById(R.id.back_drop));

            Picasso.with(this)
                    .load(posterUrl)
                    .into((ImageView) findViewById(R.id.movie_poster));
        }
    }

    @OnClick(R.id.button_watch_trailer)
    public void trailerBtnClicked() {
        presenter.trailerBtnClicked();
    }

    @Override
    public void showReviewInfo(List<Review> reviews) {
        mReviewListAdapter.add(reviews);
    }

    @Override
    public void showTrailerInfo(List<Trailer> trailers) {
        mTrailerListAdapter.add(trailers);
    }

    @Override
    public void openTrailer(Trailer trailer) {
        Uri webpage = Uri.parse(trailer.getTrailerUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void openReview(Review review) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(review.getUrl())));
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReviewclicked(Review review, int position) {
        presenter.reviewClicked(review);
    }

    @Override
    public void watch(Trailer trailer, int position) {
        presenter.trailerClicked(trailer);
    }
}
