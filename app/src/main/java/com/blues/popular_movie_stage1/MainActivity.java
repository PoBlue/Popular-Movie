package com.blues.popular_movie_stage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.blues.popular_movie_stage1.api.FetchMoviesTask;
import com.blues.popular_movie_stage1.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchMoviesTask.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new MovieFragement())
                    .commit();
        }

        new FetchMoviesTask(FetchMoviesTask.TOP_RATED, this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent settingIntent = new Intent(this,SettingsActivity.class);
                startActivity(settingIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMoviesFetchFinished(List<Movie> movies) {
        Log.v("test", movies.get(0).getTitle());
    }
}
