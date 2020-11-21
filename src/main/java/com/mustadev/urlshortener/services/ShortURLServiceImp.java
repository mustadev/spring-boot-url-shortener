package com.mustadev.urlshortener.services;


import java.util.NoSuchElementException;

import com.mustadev.urlshortener.entites.ShortURL;
import com.mustadev.urlshortener.repositores.ShortURLRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortURLServiceImp implements ShortURLService {

    @Autowired
    private ShortURLRepository shortURLRepository;
    @Override
    public ShortURL find(int id) throws NoSuchElementException {
        return shortURLRepository.findById(id).orElseThrow();
    }

    @Override
    public ShortURL save(ShortURL shortURL) {
        return shortURLRepository.save(shortURL);
    }

    @Override
    public void delete(ShortURL shortURL) {
       shortURLRepository.delete(shortURL);
    }
    
}
