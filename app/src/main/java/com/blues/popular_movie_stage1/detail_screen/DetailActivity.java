package com.blues.popular_movie_stage1.detail_screen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blues.popular_movie_stage1.R;
import com.blues.popular_movie_stage1.model.Movie;
import com.blues.popular_movie_stage1.model.Review;
import com.blues.popular_movie_stage1.model.Trailer;
import com.blues.popular_movie_stage1.root.App;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailActivityMVP.View {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String ARG_MOVIE = "ARG_MOVIE";
    private Movie mMovie;

    @Inject
    DetailActivityMVP.Presenter presenter;

    @Bind(R.id.detail_toolbar)
    Toolbar mToolbar;

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

        Intent intent = getIntent();
        mMovie = intent.getParcelableExtra(ARG_MOVIE);
        presenter.setView(this);
        presenter.getMovieInfo(mMovie);

        setSupportActionBar(mToolbar);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);
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

    @Override
    public void showReviewInfo(List<Review> reviews) {

    }

    @Override
    public void showTrailerInfo(List<Trailer> trailers) {

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
    public void showErrorMessage(String msg) {
        Log.v("test", msg);
    }
}
