package com.blues.popular_movie_stage1.api;

import com.blues.popular_movie_stage1.model.Movies;
import com.blues.popular_movie_stage1.model.Reviews;
import com.blues.popular_movie_stage1.model.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wicher on 2017/8/25.
 */

public interface MovieDatabaseService {
    @GET("3/movie/{sort_by}")
    Call<Movies> discoverMovies(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Call<Trailers> findTrailersById(@Path("id") long movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Call<Reviews> findReviewsById(@Path("id") long movieId, @Query("api_key") String apiKey);
}
