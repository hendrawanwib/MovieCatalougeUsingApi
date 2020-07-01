package haweje.app.mymoviecatalougeapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import haweje.app.mymoviecatalougeapi.Adapter.TvshowListAdapter;

import haweje.app.mymoviecatalougeapi.model.DbTvShowModel;

import haweje.app.mymoviecatalougeapi.model.Tvshow;

public class FragmentTv extends Fragment {
    private View view;
    private RecyclerView rvTvshow;
    private ArrayList<Tvshow> list = new ArrayList<>();
    private ProgressBar progressBar;
    private DbTvShowModel dbTvShowModel;
    private TvshowListAdapter tvshowListAdapter;
    public FragmentTv(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tv_fragment,container,false);
        rvTvshow = view.findViewById(R.id.tv_rv);
        progressBar = view.findViewById(R.id.progress_bar);
        tvshowListAdapter = new TvshowListAdapter();
        rvTvshow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvshow.setAdapter(tvshowListAdapter);

        dbTvShowModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DbTvShowModel.class);
        dbTvShowModel.getTvShow().observe(this, getTvShow);
        dbTvShowModel.setTvShow("EXTRA_TV_SHOW");
        showLoading(true);
        return view;
    }

    private Observer<ArrayList<Tvshow>> getTvShow = new Observer<ArrayList<Tvshow>>() {
        @Override
        public void onChanged(ArrayList<Tvshow> tvshows) {
            if (tvshows != null){
                tvshowListAdapter.setData(tvshows);
            }

            showLoading(false);
        }
    };

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
