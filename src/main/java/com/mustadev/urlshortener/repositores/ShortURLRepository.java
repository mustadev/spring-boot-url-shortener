package com.mustadev.urlshortener.repositores;

import com.mustadev.urlshortener.entites.ShortURL;

import org.springframework.data.repository.CrudRepository;

public interface ShortURLRepository  extends CrudRepository<ShortURL, Long> {
    
    ShortURL findByShortURL(String shortURL);
}
