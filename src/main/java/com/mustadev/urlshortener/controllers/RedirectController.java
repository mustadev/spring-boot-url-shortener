package com.mustadev.urlshortener.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    
    @GetMapping("/short/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl){
        // TODO validate shorturl.
        System.out.println("shrt url " + shortUrl);
        URI url = URI.create(shortUrl);
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
            .location(url)
            .build();
    }
}
