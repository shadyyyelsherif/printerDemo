package com.example.shadi.printerdemo;

/**
 * Created by ShaDi on 2/12/2017.
 */

public class Blog {

    private String time ;
    private String name;
    private String Filament;
    private String image;

    public Blog()
    {

    }
    public Blog(String time, String name, String Filament,String image) {
        this.time  = time;
        this.name = name;
        this.Filament = Filament;
        this.image=image;
    }

    public String getFilament() {
        return Filament;
    }

    public void setFilament(String Filament) {
        this.Filament = Filament;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String gettime() {
        return time ;
    }

    public void setTitle(String time) {
        this.time = time;
    }


}
