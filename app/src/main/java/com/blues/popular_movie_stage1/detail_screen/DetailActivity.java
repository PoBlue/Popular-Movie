package com.blues.popular_movie_stage1.detail_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blues.popular_movie_stage1.R;
import com.blues.popular_movie_stage1.model.Movie;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String ARG_MOVIE = "ARG_MOVIE";
    private Movie mMovie;

    @Inject
    DetailActivityMVP.Presenter presenter;

    @Bind(R.id.detail_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        mMovie = intent.getParcelableExtra(ARG_MOVIE);

        if (intent != null) {
            String title = mMovie.getTitle();
            String posterUrl = mMovie.getPosterUrl(this);
            String overview = mMovie.getOverview();
            String vote = mMovie.getUserRating();
            String date = mMovie.getReleaseDate();

            ((TextView) findViewById(R.id.movie_title)).setText(title);
            ((TextView) findViewById(R.id.movie_overview)).setText(overview);
            ((TextView) findViewById(R.id.movie_rating)).setText("VOTE: " + vote);
            ((TextView) findViewById(R.id.movie_release_date)).setText("DATE: " + date);

            Picasso.with(this)
                    .load(posterUrl)
                    .into((ImageView) findViewById( R.id.back_drop));

            Picasso.with(this)
                    .load(posterUrl)
                    .into((ImageView) findViewById( R.id.movie_poster));
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
