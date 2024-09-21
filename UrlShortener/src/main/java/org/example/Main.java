package org.example;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        UrlShortener urlShortener = new UrlShortener(new HashMap<>(), new HashMap<>(),
                "http://revo.lut", 8, new Random());

        String shortUrl = urlShortener.shortenUrl("https://azerconnect.az/domain/flag?salam=manis");
        System.out.println(shortUrl);
        System.out.println(urlShortener.expandUrl(shortUrl));
    }
}