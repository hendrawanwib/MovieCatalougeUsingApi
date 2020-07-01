package haweje.app.mymoviecatalougeapi.detail_activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import haweje.app.mymoviecatalougeapi.R;
import haweje.app.mymoviecatalougeapi.model.Tvshow;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String TV_SHOW = "tvshow";
    private static final String STATE_NAME = "state_name";
    private static final String STATE_DATE = "state_date";
    private static final String STATE_DESC = "state_desc";


    ProgressBar progressBar;
    TextView tvName, tvDesc, tvDate;
    ImageView imgPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);
        tvName = findViewById(R.id.tv_item_name);
        tvDate = findViewById(R.id.tv_item_date);
        tvDesc = findViewById(R.id.text_desc);
        imgPhoto = findViewById(R.id.img_photo);

        progressBar = findViewById(R.id.loadingmoviebar);
        progressBar.setVisibility(View.VISIBLE);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(STATE_NAME);
            tvName.setText(name);
            String date = savedInstanceState.getString(STATE_DATE);
            tvDate.setText(date);
            String desc = savedInstanceState.getString(STATE_DESC);
            tvDesc.setText(desc);

        }

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {}

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Tvshow tvshow = getIntent().getParcelableExtra(TV_SHOW);

                        String url_image = "https://image.tmdb.org/t/p/w185" + tvshow.getPhoto();


                        tvName.setText(tvshow.getName());
                        tvDate.setText(tvshow.getDate());
                        tvDesc.setText(tvshow.getDesc());
                        Glide.with(TvShowDetailActivity.this)
                                .load(url_image)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imgPhoto);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }

        }).start();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_NAME, tvName.getText().toString());
        outState.putString(STATE_DATE, tvDate.getText().toString());
        outState.putString(STATE_DESC, tvDesc.getText().toString());
        super.onSaveInstanceState(outState);

    }

    }

