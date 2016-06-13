package com.example.android.androidapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anjana on 6/1/16.
 */

public class Results implements Parcelable {
    @SerializedName("vote_average")
    @Expose
    private String vote_average;

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;

    @SerializedName("adult")
    @Expose
    private String adult;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("original_language")
    @Expose
    private String original_language;

    @SerializedName("genre_ids")
    @Expose
    private ArrayList<String> genre_ids;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("original_title")
    @Expose
    private String original_title;

    @SerializedName("vote_count")
    @Expose
    private String vote_count;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("video")
    @Expose
    private String video;

    @SerializedName("popularity")
    @Expose
    private String popularity;

    public String getVote_average ()
    {
        return vote_average;
    }

    public void setVote_average (String vote_average)
    {
        this.vote_average = vote_average;
    }

    public String getBackdrop_path ()
    {
        return backdrop_path;
    }

    public void setBackdrop_path (String backdrop_path)
    {
        this.backdrop_path = backdrop_path;
    }

    public String getAdult ()
    {
        return adult;
    }

    public void setAdult (String adult)
    {
        this.adult = adult;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public String getOriginal_language ()
    {
        return original_language;
    }

    public void setOriginal_language (String original_language)
    {
        this.original_language = original_language;
    }

    public ArrayList<String> getGenre_ids ()
    {
        return genre_ids;
    }

    public void setGenre_ids (ArrayList<String> genre_ids)
    {
        this.genre_ids = genre_ids;
    }

    public String getRelease_date ()
    {
        return release_date;
    }

    public void setRelease_date (String release_date)
    {
        this.release_date = release_date;
    }

    public String getOriginal_title ()
    {
        return original_title;
    }

    public void setOriginal_title (String original_title)
    {
        this.original_title = original_title;
    }

    public String getVote_count ()
    {
        return vote_count;
    }

    public void setVote_count (String vote_count)
    {
        this.vote_count = vote_count;
    }

    public String getPoster_path ()
    {
        return poster_path;
    }

    public void setPoster_path (String poster_path)
    {
        this.poster_path = poster_path;
    }

    public String getVideo ()
    {
        return video;
    }

    public void setVideo (String video)
    {
        this.video = video;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vote_average = "+vote_average+", backdrop_path = "+backdrop_path+", adult = "+adult+", id = "+id+", title = "+title+", overview = "+overview+", original_language = "+original_language+", genre_ids = "+genre_ids+", release_date = "+release_date+", original_title = "+original_title+", vote_count = "+vote_count+", poster_path = "+poster_path+", video = "+video+", popularity = "+popularity+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vote_average);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.adult);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.original_language);
        dest.writeStringList(this.genre_ids);
        dest.writeString(this.release_date);
        dest.writeString(this.original_title);
        dest.writeString(this.vote_count);
        dest.writeString(this.poster_path);
        dest.writeString(this.video);
        dest.writeString(this.popularity);
    }

    public Results() {
    }

    protected Results(Parcel in) {
        this.vote_average = in.readString();
        this.backdrop_path = in.readString();
        this.adult = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.original_language = in.readString();
        this.genre_ids = in.createStringArrayList();
        this.release_date = in.readString();
        this.original_title = in.readString();
        this.vote_count = in.readString();
        this.poster_path = in.readString();
        this.video = in.readString();
        this.popularity = in.readString();
    }

    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
