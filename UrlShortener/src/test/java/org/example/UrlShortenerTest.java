package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UrlShortenerTest {
    UrlShortener urlShortener;

    @BeforeEach
    void preProcess() {
        HashMap<String, String> longMap = new HashMap<>();
        HashMap<String, String> shortMap = new HashMap<>();
        longMap.put("long", "short");
        shortMap.put("short", "long");

        urlShortener = new UrlShortener(longMap, shortMap, "http://revo.lut", 8, new Random());
    }

    @Test
    void shortenUrl() {
        String s = urlShortener.shortenUrl("http://salam/qardas/necesen?cavab=yaxsiyam");
        assertEquals(8, s.length() - urlShortener.getDomain().length() - 1);
        assertEquals(s.substring(urlShortener.getDomain().length() + 1),
                urlShortener.getLongUrlMap().get("salam/qardas/necesen?cavab=yaxsiyam"));
    }

    @Test
    void expandUrl() {
        assertEquals("long", urlShortener.expandUrl(urlShortener.getDomain() + "/" + "short"));
        assertThrows(IllegalArgumentException.class, () -> urlShortener.expandUrl("http://hel.lo/heyyo"));
    }

    @Test
    void adjustUrl() {
        assertEquals("salam", urlShortener.adjustUrl("http://salam"));
        assertEquals("salam", urlShortener.adjustUrl("https://salam"));
        assertEquals("salam", urlShortener.adjustUrl("http://salam/"));
    }

    @Test
    void saveUrl() {
        int longUrlMapSize = urlShortener.getLongUrlMap().size();
        int shortUlrMapSize = urlShortener.getShortUrlMap().size();
        urlShortener.shortenUrl("salam/meni/yadina/salan/dostlara");
        assertEquals(longUrlMapSize + 1, urlShortener.getLongUrlMap().size());
        assertEquals(shortUlrMapSize + 1, urlShortener.getShortUrlMap().size());
    }

    @Test
    void generateUrl() {
        String url1 = urlShortener.generateUrl("revolut/will/hire/me");
        String url2 = urlShortener.generateUrl("revolut/will/hire/me");
        assertNotEquals(url1, url2);
    }
}