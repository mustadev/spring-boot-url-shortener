package com.mustadev.urlshortener.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shorturls")
@Data
public class ShortURL {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
    
	@Column(name = "shorturl", nullable = false, updatable = false)
    private String shortURL;

    @Column(name = "longurl", nullable = false, unique = true)
    private String longURL;

}
