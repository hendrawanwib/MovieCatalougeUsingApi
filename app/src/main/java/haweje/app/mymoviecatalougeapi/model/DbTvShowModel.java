package haweje.app.mymoviecatalougeapi.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DbTvShowModel extends ViewModel {
    private static final String API_KEY = "d131c64c9f047f3fdd4e53755f1dbb14";
    private MutableLiveData<ArrayList<Tvshow>> listTvShow = new MutableLiveData<>();

    public void setTvShow (final String tvShow) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Tvshow> listItem = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        Tvshow tvshowItems = new Tvshow(tv);
                        listItem.add(tvshowItems);
                    }
                    listTvShow.postValue(listItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure :", error.getMessage());
            }
        });

    }
    public LiveData<ArrayList<Tvshow>> getTvShow(){
        return listTvShow;
    }
}
