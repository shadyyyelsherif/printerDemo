package com.example.shadi.printerdemo;

/**
 * Created by ShaDi on 2/12/2017.
 */

public class Blog {

    private String time ;
    private String layer;
    private String image;

    public Blog()
    {

    }
    public Blog(String time, String layer, String image) {
        this.time  = time;
        this.layer = layer;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getlayer() {
        return layer;
    }

    public void setlayer(String layer) {
        this.layer = layer;
    }

    public String gettime() {
        return time ;
    }

    public void setTitle(String time) {
        this.time = time;
    }


}
