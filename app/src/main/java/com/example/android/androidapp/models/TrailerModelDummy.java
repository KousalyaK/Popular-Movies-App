package com.example.android.androidapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anjana on 6/13/16.
 */
public class TrailerModelDummy implements Parcelable
{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("results")
    @Expose
    private ArrayList<TrailerResults> results;

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("total_pages")
    @Expose
    private String total_pages;

    @SerializedName("total_results")
    @Expose
    private String total_results;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ArrayList<TrailerResults> getResults ()
    {
        return results;
    }

    public void setResults (ArrayList<TrailerResults> results)
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
        return "ClassPojo [id = "+id+", results = "+results+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeTypedList(this.results);
        dest.writeString(this.page);
        dest.writeString(this.total_pages);
        dest.writeString(this.total_results);
    }

    public TrailerModelDummy() {
    }

    protected TrailerModelDummy(Parcel in) {
        this.id = in.readString();
        this.results = in.createTypedArrayList(TrailerResults.CREATOR);
        this.page = in.readString();
        this.total_pages = in.readString();
        this.total_results = in.readString();
    }

    public static final Creator<TrailerModelDummy> CREATOR = new Creator<TrailerModelDummy>() {
        @Override
        public TrailerModelDummy createFromParcel(Parcel source) {
            return new TrailerModelDummy(source);
        }

        @Override
        public TrailerModelDummy[] newArray(int size) {
            return new TrailerModelDummy[size];
        }
    };
}