package haweje.app.mymoviecatalougeapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {
    String photo;
    String title;
    String release_date;
    String overview;

    public Movie(JSONObject movie) {
        try {
            String name = movie.getString("title");
            String date = movie.getString("release_date");
            String photo = movie.getString("poster_path");
            String desc = movie.getString("overview");

            this.overview = desc;
            this.title = name;
            this.release_date = date;
            this.photo = photo;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getDate() {
        return release_date;
    }

    public void setDate(String date) {
        this.release_date = date;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String desc){
        this.overview = desc;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
    }

    protected Movie(Parcel in) {
        this.photo = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
