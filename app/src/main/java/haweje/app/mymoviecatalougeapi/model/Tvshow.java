package haweje.app.mymoviecatalougeapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Tvshow implements Parcelable {
    String photo;
    String name;
    String date;
    String desc;

    public Tvshow(JSONObject tvShow) {
        try {
            String name = tvShow.getString("name");
            String date = tvShow.getString("first_air_date");
            String photo = tvShow.getString("poster_path");
            String desc = tvShow.getString("overview");

            this.desc = desc;
            this.name = name;
            this.date = date;
            this.photo = photo;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.date);
        dest.writeString(this.desc);
    }

    public Tvshow() {
    }

    protected Tvshow(Parcel in) {
        this.photo = in.readString();
        this.name = in.readString();
        this.date = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<Tvshow> CREATOR = new Parcelable.Creator<Tvshow>() {
        @Override
        public Tvshow createFromParcel(Parcel source) {
            return new Tvshow(source);
        }

        @Override
        public Tvshow[] newArray(int size) {
            return new Tvshow[size];
        }
    };
}
