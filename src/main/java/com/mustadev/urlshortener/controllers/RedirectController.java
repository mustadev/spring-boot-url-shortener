package com.mustadev.urlshortener.controllers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import com.mustadev.urlshortener.entites.ShortURL;
import com.mustadev.urlshortener.services.ShortURLService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/short")
public class RedirectController {

    @Autowired
    private ShortURLService shortURLService;

    @GetMapping("/{shortLink}")
    public ResponseEntity redirect(@PathVariable String shortLink) {
        // TODO validate shorturl.
        System.out.println("shrt url " + shortLink);
        var id = this.decode(shortLink);
        try {
            var shortURL = shortURLService.find(id);
            return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(new URL(shortURL.getLongURL()).toURI())
                    .build();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("mal formed Exception");
        }
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}, 
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> generateShortURL(@RequestBody Map<String, String> requestBody) {
        var response = new HashMap<String, String>();
        var longURL = requestBody.get("longURL");
        try {
            new URL(longURL).toURI();
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "failed");
            response.put("error", "mal formed URL: " + longURL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        ShortURL shortURL = new ShortURL();
        shortURL.setLongURL(longURL);
        shortURL = shortURLService.save(shortURL);
        var shortURLLink = this.encode(shortURL.getId());
        response.put("message", "success");
        response.put("longURL", longURL);
        response.put("shortURL", "http://localhost:8080/short/" + shortURLLink);
        System.out.println("longURL : " + longURL);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
