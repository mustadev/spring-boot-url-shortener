package com.mustadev.urlshortener.entites;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShortURL {
    @Id
    private String shortURL;

    private String longURL;


    public void setShortURL(String shortURL){
        this.shortURL = shortURL;
    }
    public String getShortURL(){
        return this.shortURL;
    }


    public void setlongURL(String longURL){
        this.longURL = longURL;
    }
    public String getLongURL(){
        return this.longURL;
    }


}
