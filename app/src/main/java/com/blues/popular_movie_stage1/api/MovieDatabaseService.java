package com.blues.popular_movie_stage1.api;

import com.blues.popular_movie_stage1.model.Movies;
import com.blues.popular_movie_stage1.model.Reviews;
import com.blues.popular_movie_stage1.model.Trailers;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wicher on 2017/8/25.
 */

public interface MovieDatabaseService {
    @GET("3/movie/{sort_by}")
    Observable<Movies> discoverMovies(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Observable<Trailers> findTrailersById(@Path("id") long movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Observable<Reviews> findReviewsById(@Path("id") long movieId, @Query("api_key") String apiKey);
}
