package com.blues.popular_movie_stage1.detail_screen;

import com.blues.popular_movie_stage1.api.MovieDatabaseService;
import com.blues.popular_movie_stage1.movie_list.MovieModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wicher on 2017/8/31.
 */

@Module
public class DetailModule {
    @Provides
    public DetailActivityMVP.Model provideModel(MovieDatabaseService service){
        return new MovieModel(service);
    }

    @Provides
    public DetailActivityMVP.Presenter providePresenter(DetailActivityMVP.Model model){
        return new DetailPresenter(model);
    }
}
