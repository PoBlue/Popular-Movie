package com.blues.popular_movie_stage1.api;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wicher on 2017/8/30.
 */

@Module
public class MovieApiModule {
    public final String BASE_URL = "http://api.themoviedb.org/";

    @Provides
    public Retrofit provideRetrofit(String baseURL){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    public MovieDatabaseService provideApiService() {
        return provideRetrofit(BASE_URL).create(MovieDatabaseService.class);
    }
}
