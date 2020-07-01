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
import haweje.app.mymoviecatalougeapi.detail_activity.TvShowDetailActivity;

import haweje.app.mymoviecatalougeapi.model.Tvshow;

public class TvshowListAdapter extends RecyclerView.Adapter<TvshowListAdapter.ListViewHolder> {

    private ArrayList<Tvshow> listTvshow = new ArrayList<>();

    public void setData(ArrayList<Tvshow> items){
        listTvshow.clear();
        listTvshow.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.bind(listTvshow.get(position));
    }

    @Override
    public int getItemCount() {
        return listTvshow.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName, tvDate;
        LinearLayout item_list;

        ListViewHolder(View itemView) {
            super(itemView);
            item_list = itemView.findViewById(R.id.list_item);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.text_desc);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);

        }

        void bind(Tvshow tvshow){
            String url_image = "https://image.tmdb.org/t/p/w185" + tvshow.getPhoto();
            tvName.setText(tvshow.getName());
            tvDate.setText(tvshow.getDate());
            Glide.with(itemView.getContext())
                    .load(url_image)
                    .into(imgPhoto);

        }

        @Override
        public void onClick(View view) {
            int positionn = getAdapterPosition();
            Tvshow tvshow = listTvshow.get(positionn);
            Intent mvDetailTvShow = new Intent(itemView.getContext(), TvShowDetailActivity.class);
            mvDetailTvShow.putExtra(TvShowDetailActivity.TV_SHOW, tvshow);
            itemView.getContext().startActivity(mvDetailTvShow);

        }
    }
}
