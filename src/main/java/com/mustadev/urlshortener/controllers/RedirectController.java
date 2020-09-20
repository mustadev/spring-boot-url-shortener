package com.mustadev.urlshortener.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.util.Random;

import com.mustadev.urlshortener.entites.ShortURL;
import com.mustadev.urlshortener.services.ShortURLService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    
    @Autowired
    private ShortURLService shortURLService;

    @GetMapping("/short/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl){
        // TODO validate shorturl.
        System.out.println("shrt url " + shortUrl);
        URI url = URI.create(shortUrl);
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
            .location(url)
            .build();
    }

    @PostMapping()
    public ResponseEntity<ShortURL> generateShortURL(@RequestBody String longURL){
        ShortURL shortURL = new ShortURL();
        shortURL.setLongURL(longURL);
        shortURL.setShortURL("localhost:8080/" + this.generateRandomString(4));
        shortURL = shortURLService.save(shortURL);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortURL);
    }

    public  String generateRandomString(int length){
        Random rnd = new Random();
        String chars = "azertyuiopqsdmwxcvbn";
        String randomString = ""; 
        for (int i = 0; i < length; i++){
            randomString += chars.charAt(rnd.nextInt(chars.length()));
        }

        return randomString;
    }
}
