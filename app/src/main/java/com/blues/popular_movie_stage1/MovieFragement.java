package com.blues.popular_movie_stage1;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieFragement extends Fragment {

    private final String LOG_TAG = MovieFragement.class.getSimpleName();

    private MovieData mMovieData;
    private MovieAdapter mImageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public MovieFragement() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String order = pref.getString(getString(R.string.pref_units_key),getString(R.string.pref_units_popular));
        Log.v(LOG_TAG,"order: " + order);
        new FetchMovieTask().execute(order);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_main,container, false);
//        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mImageAdapter = new MovieAdapter();

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mImageAdapter);


//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    Intent detailIntent = new Intent(getActivity(),DetailActivity.class)
//                            .putExtra("title",mMovieData.getTitle(position))
//                            .putExtra("date",mMovieData.getDate(position))
//                            .putExtra("poster",mMovieData.getPosterUrl(position))
//                            .putExtra("overview",mMovieData.getIntroduce(position))
//                            .putExtra("vote",mMovieData.getVote(position)
//                            );
//
//                    startActivity(detailIntent);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

        return rootView;
    }


    public class FetchMovieTask extends AsyncTask<String,Void,String>{

        private final String LOG_TAG = FetchMovieTask.class.getSimpleName();

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String moviesJsonStr = null;
            String orderPath = params[0];

            //TODO: replace {API_KEY} with your API_KEY
            String apiKey = "ab91ed9affc29a894989e8ea3200d963";

            try {
                final String MOVIE_BASE_URL = "http://api.themoviedb.org/3";
                final String POPULAR_PATH = "popular";
                final String MOVIE_PATH = "movie";
                final String VIDEOS_PATH = "videos";
                final String REVIEWS_PATH = "reviews";
                final String TOP_RATED_PATH = "top_rated";
                final String API_PARAM = "api_key";

                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendPath(MOVIE_PATH)
                        .appendPath(orderPath)
                        .appendQueryParameter(API_PARAM,apiKey)
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG,"Built URI: " + builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                moviesJsonStr = buffer.toString();

            } catch (IOException e) {
                Log.e(LOG_TAG,"Error",e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("ForecastFragment", "Error closing stream", e);
                    }
                }
            }

            return moviesJsonStr;
        }

        @Override
        protected void onPostExecute(String moviesJsonStr) {
            super.onPostExecute(moviesJsonStr);
            if (moviesJsonStr != null){
                mMovieData = new MovieData(moviesJsonStr);
                List<String> imageUrls = mMovieData.getAllPosterUrls();
                mImageAdapter.updateImageUrls(imageUrls);
                Log.v(LOG_TAG,imageUrls.get(3));

            }
        }
    }

    public class MovieData {
        String LOG_TAG = MovieData.class.getSimpleName();
        private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";

        private JSONObject movieJson;

        public MovieData(String jsonStr){
            try {
                movieJson = new JSONObject(jsonStr);
            } catch (JSONException e) {
                Log.v(LOG_TAG,"Error: ",e);
            }
        }

        String getPosterUrl(int position) {
            try {
                return IMAGE_BASE_URL + getResult(position).getString("poster_path");
            } catch (JSONException e) {
                Log.v(LOG_TAG,"Error: ",e);
                return null;
            }
        }

        List<String> getAllPosterUrls(){
            List<String> urls = new ArrayList<String>();

            for (int i = 0 ; i < getResults().length();i++){
               urls.add(getPosterUrl(i));
            }

            return urls;
        }

        JSONArray getResults() {
            try {
                return movieJson.getJSONArray("results");
            } catch (JSONException e) {
                Log.v(LOG_TAG,"Error: ",e);
                return null;
            }
        }

        JSONObject getResult(int position){
            try {
                return getResults().getJSONObject(position);
            } catch (JSONException e) {
                Log.v(LOG_TAG,"Error: ",e);
                return null;
            }
        }

        String getTitle(int position) throws JSONException{
                return getResult(position).getString("title");
        }

        String getIntroduce(int position) throws JSONException{
            return getResult(position).getString("overview");
        }

        String getDate(int position) throws JSONException{
            return getResult(position).getString("release_date");
        }

        int getVote(int position) throws JSONException {
            return getResult(position).getInt("vote_average");
        }




    }

}
