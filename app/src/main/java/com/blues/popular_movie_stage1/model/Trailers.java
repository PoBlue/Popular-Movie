package com.blues.popular_movie_stage1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wicher on 2017/8/26.
 */

public class Trailers {
    @SerializedName("results")
    private List<Trailer> trailers = new ArrayList<>();

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
