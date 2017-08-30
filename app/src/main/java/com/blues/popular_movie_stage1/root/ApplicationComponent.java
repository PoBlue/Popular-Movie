package com.blues.popular_movie_stage1.root;

import com.blues.popular_movie_stage1.api.MovieApiModule;
import com.blues.popular_movie_stage1.movie_list.MovieFragement;
import com.blues.popular_movie_stage1.movie_list.MovieModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wicher on 2017/8/30.
 */

@Singleton
@Component(modules = {ApplicationModule.class, MovieApiModule.class, MovieModule.class})
public interface ApplicationComponent {
    void inject(MovieFragement target);
}
