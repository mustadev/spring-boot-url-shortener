package com.mustadev.urlshortener.services;


import java.util.NoSuchElementException;

import com.mustadev.urlshortener.entites.ShortURL;

public interface ShortURLService {
    
    public ShortURL find(int id) throws NoSuchElementException;

    public ShortURL save(ShortURL shortURL);

    public void delete(ShortURL shortURL);
}
