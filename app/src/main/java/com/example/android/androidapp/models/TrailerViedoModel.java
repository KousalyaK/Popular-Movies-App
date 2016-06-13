package com.example.android.androidapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anjana on 6/13/16.
 */
public class TrailerViedoModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("results")
    @Expose
    private ArrayList<TrailersResultList> results;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ArrayList<TrailersResultList> getResults ()
    {
        return results;
    }

    public void setResults (ArrayList<TrailersResultList> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", results = "+results+"]";
    }
}
