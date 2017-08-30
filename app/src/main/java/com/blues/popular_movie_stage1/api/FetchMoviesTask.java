package com.blues.popular_movie_stage1.api;

import android.os.AsyncTask;
import android.support.annotation.StringDef;

import com.blues.popular_movie_stage1.model.Movie;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by wicher on 2017/8/25.
 */

public class FetchMoviesTask extends AsyncTask<Void, Void, List<Movie>>{

    @SuppressWarnings("unused")
    public static String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    public final static String MOST_POPULAR = "popular";
    public final static String TOP_RATED = "top_rated";
    public final static String FAVORITES = "favorites";

    // FetchMoviesTask cannot load favorites movies now, it's done by loaders (especially for two pane is
    // comfortable - without force updating left pane on removing/adding a favorite movie. Another
    // case when we simple returns from detail - list of favorites also will be updated, if needed).
    @StringDef({MOST_POPULAR, TOP_RATED, FAVORITES})
    public @interface SORT_BY {
    }

    /**
     * Will be called in {@link FetchMoviesTask#onPostExecute(List)} to notify subscriber to about
     * task completion.
     */
    private
    @SORT_BY
    String mSortBy = MOST_POPULAR;

    private final Listener mListener;
    /**
     * Interface definition for a callback to be invoked when movies are loaded.
     */
    public interface Listener {
        void onMoviesFetchFinished(List<Movie> movies);
    }

    public FetchMoviesTask(@SORT_BY String sortBy, Listener listener) {
        mListener = listener;
        mSortBy = sortBy;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies != null) {
            mListener.onMoviesFetchFinished(movies);
        } else {
            mListener.onMoviesFetchFinished(new ArrayList<Movie>());
        }
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.themoviedb.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        MovieDatabaseService service = retrofit.create(MovieDatabaseService.class);
//        Call<Movies> call = service.discoverMovies(mSortBy,
//                BuildConfig.THE_MOVIE_DATABASE_API_KEY);
//        try {
//            Response<Movies> response = call.execute();
//            Movies movies = response.body();
//            return movies.getMovies();
//
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "A problem occurred talking to the movie db ", e);
//        }
        return null;
    }
}
