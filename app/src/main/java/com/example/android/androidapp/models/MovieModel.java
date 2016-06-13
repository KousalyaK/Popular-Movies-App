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
public class MovieModel implements Parcelable {

    @SerializedName("results")
    @Expose
    private ArrayList<Results> results;

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("total_pages")
    @Expose
    private String total_pages;

    @SerializedName("total_results")
    @Expose
    private String total_results;

    public ArrayList<Results> getResults ()
    {
        return results;
    }

    public void setResults (ArrayList<Results>results)
    {
        this.results = results;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
        dest.writeString(this.page);
        dest.writeString(this.total_pages);
        dest.writeString(this.total_results);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.results = new ArrayList<Results>();
        in.readList(this.results, Results.class.getClassLoader());
        this.page = in.readString();
        this.total_pages = in.readString();
        this.total_results = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
