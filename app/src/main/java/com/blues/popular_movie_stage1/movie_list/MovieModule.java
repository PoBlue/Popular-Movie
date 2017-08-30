package com.blues.popular_movie_stage1.movie_list;

import com.blues.popular_movie_stage1.api.MovieDatabaseService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wicher on 2017/8/30.
 */

@Module
public class MovieModule {
    @Provides
    public MovieListActivityMVP.Presenter providePresenter(MovieListActivityMVP.Model movieModel){
        return new MoviePresenter(movieModel);
    }

    @Provides
    public MovieListActivityMVP.Model provideModel(MovieDatabaseService service){
        return new MovieModel(service);
    }
}
