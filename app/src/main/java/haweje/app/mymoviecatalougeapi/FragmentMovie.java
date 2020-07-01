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

import haweje.app.mymoviecatalougeapi.Adapter.MovieListAdapter;
import haweje.app.mymoviecatalougeapi.model.DbMovieModel;
import haweje.app.mymoviecatalougeapi.model.Movie;

public class FragmentMovie extends Fragment {

    private View view;
    private RecyclerView rvMovies;
    private ProgressBar progressBar;
    private DbMovieModel dbMovieModel;
    private MovieListAdapter movieListAdapter;
    public FragmentMovie() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_fragment, container, false);
        rvMovies = view.findViewById(R.id.movie_rv);
        progressBar = view.findViewById(R.id.progress_bar);
        movieListAdapter = new MovieListAdapter();
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.setAdapter(movieListAdapter);

        dbMovieModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DbMovieModel.class);
        dbMovieModel.getMovie().observe(this, getMovie);
        dbMovieModel.setMovie("EXTRA_MOVIE");

        showLoading(true);
        return view;
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null){
                movieListAdapter.setData(movies);
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
