package com.mustadev.urlshortener.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.util.HashMap;
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

    @GetMapping("/short/{shortLink}")
    public ResponseEntity<String> redirect(@PathVariable String shortLink) {
        // TODO validate shorturl.
        System.out.println("shrt url " + shortLink);
        // URI url = URI.create(shortUrl);
        // return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(url).build();
        var id = this.decode(shortLink);
        var shortURL = shortURLService.find(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(shortURL.getLongURL());
    }

    @PostMapping("/long")
    public ResponseEntity<String> generateShortURL(@RequestBody String longURL){
        ShortURL shortURL = new ShortURL();
        shortURL.setLongURL(longURL);
        shortURL = shortURLService.save(shortURL);
        var shortURLLink = this.encode(shortURL.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(shortURLLink);
    }

    

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    private String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt(num % BASE));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    private int decode(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++)
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        return num;
    }

}
