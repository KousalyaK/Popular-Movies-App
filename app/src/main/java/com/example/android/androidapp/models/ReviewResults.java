package com.example.android.androidapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anjana on 6/14/16.
 */
public class ReviewResults {

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("url")
    @Expose
    private String url;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", id = "+id+", author = "+author+", url = "+url+"]";
    }
}
