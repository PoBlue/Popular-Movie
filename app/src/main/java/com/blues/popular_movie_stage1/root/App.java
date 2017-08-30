package com.blues.popular_movie_stage1.root;

import android.app.Application;

import com.blues.popular_movie_stage1.api.MovieApiModule;
import com.blues.popular_movie_stage1.movie_list.MovieModule;

/**
 * Created by wicher on 2017/8/30.
 */

public class App extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .movieApiModule(new MovieApiModule())
                .movieModule(new MovieModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
