package com.blues.popular_movie_stage1.movie_list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blues.popular_movie_stage1.R;
import com.blues.popular_movie_stage1.model.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blues on 2017/2/10.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;
    private ClickMovieHandler handler;

    public void setHandler(ClickMovieHandler handler) {
        this.handler = handler;
    }

    interface ClickMovieHandler {
        void clickMovie(Movie movie);
    }

    public MovieAdapter(Context context, List<Movie> movies){
        mContext = context;
        mMovies = movies;
    }

    public void clear(){
        mMovies = new ArrayList<Movie>();
        notifyDataSetChanged();
    }


    public void updateMovies(List<Movie> movies){
        mMovies = movies;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.clickMovie(movie);
            }
        });

        holder.titleView.setText(movie.getTitle());

        Picasso.with(mContext).load(movie.getPosterUrl(mContext)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                holder.movieImageViewHD.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.toArray().length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView movieImageViewHD;
        public TextView titleView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            movieImageViewHD = (ImageView) itemView.findViewById(R.id.movie_imageview);
        }
    }
}
