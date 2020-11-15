package com.mustadev.urlshortener.services;


import com.mustadev.urlshortener.entites.ShortURL;

public interface ShortURLService {
    
    public ShortURL find(int id);

    public ShortURL save(ShortURL shortURL);

    public void delete(ShortURL shortURL);
}
