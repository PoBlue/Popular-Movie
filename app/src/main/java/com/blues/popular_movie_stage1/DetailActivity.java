package com.blues.popular_movie_stage1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blues.popular_movie_stage1.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String ARG_MOVIE = "ARG_MOVIE";
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mMovie = intent.getParcelableExtra(ARG_MOVIE);


        if (intent != null) {
            String title = mMovie.getTitle();
            String posterUrl = mMovie.getPosterUrl(this);
            String overview = mMovie.getOverview();
            String vote = mMovie.getUserRating();
            String date = mMovie.getReleaseDate();

            ((TextView) findViewById(R.id.title_view)).setText(title);
            ((TextView) findViewById(R.id.introduce_view)).setText(overview);
            ((TextView) findViewById(R.id.rated_view)).setText("VOTE: " + vote);
            ((TextView) findViewById(R.id.date_view)).setText("DATE: " + date);

            Picasso.with(this)
                    .load(posterUrl)
                    .into((ImageView) findViewById( R.id.poseter_view));

        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {


        return super.onCreateView(name, context, attrs);
    }
}
