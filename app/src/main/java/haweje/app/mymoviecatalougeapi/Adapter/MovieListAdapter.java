package haweje.app.mymoviecatalougeapi.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import haweje.app.mymoviecatalougeapi.R;
import haweje.app.mymoviecatalougeapi.detail_activity.MovieDetailActivity;
import haweje.app.mymoviecatalougeapi.model.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ListViewHolder> {

    private ArrayList<Movie> listMovie = new ArrayList<>();
    public void setData(ArrayList<Movie> items){
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        holder.bind(listMovie.get(position));
            }



    @Override
    public int getItemCount() {
        return listMovie.size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName, tvDate;
        LinearLayout item_list;

        ListViewHolder(View itemView) {
            super(itemView);
            item_list = itemView.findViewById(R.id.list_item);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.text_desc);

            itemView.setOnClickListener(this);
        }

        void bind(Movie movie){
            String url_image = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();


            tvName.setText(movie.getName());
            tvDate.setText(movie.getDate());
            Glide.with(itemView.getContext())
                    .load(url_image)
                    .into(imgPhoto);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = listMovie.get(position);
            Intent mvDetailMovie = new Intent(itemView.getContext(), MovieDetailActivity.class);
            mvDetailMovie.putExtra(MovieDetailActivity.MOVIE_LIST, movie);
            itemView.getContext().startActivity(mvDetailMovie);


        }
    }


}
