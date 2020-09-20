package com.mustadev.urlshortener.repositores;

import com.mustadev.urlshortener.entites.ShortURL;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortURLRepository  extends CrudRepository<ShortURL, Long> {
    
    ShortURL findByShortURL(String shortURL);
}
