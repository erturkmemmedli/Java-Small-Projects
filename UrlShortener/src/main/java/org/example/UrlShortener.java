package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UrlShortener {
    private Map<String, String> longUrlMap;
    private Map<String, String> shortUrlMap;
    private String domain;
    private Integer shortUrlLength;
    private Random random;
    private String longUrlHead = "";
    private char[] ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public UrlShortener(HashMap<String, String> longUrlMap,
                        HashMap<String, String> shortUrlMap,
                        String domain,
                        Integer shortUrlLength,
                        Random random) {
        this.longUrlMap = longUrlMap;
        this.shortUrlMap = shortUrlMap;
        this.domain = domain;
        this.shortUrlLength = shortUrlLength;
        this.random = random;
    }

    public String shortenUrl(String longUrl) {
        String adjustedUrl = adjustUrl(longUrl);

        if (longUrlMap.containsKey(adjustedUrl)) {
            return domain + "/" + longUrlMap.get(longUrl);
        }

        String generatedShortUrl = generateUrl(adjustedUrl);
        saveUrl(generatedShortUrl, adjustedUrl);
        return domain + "/" + generatedShortUrl;
    }

    public String expandUrl(String shortUrl) {
        shortUrl = shortUrl.substring(domain.length() + 1);
        if (!shortUrlMap.containsKey(shortUrl)) {
            throw new IllegalArgumentException("This short URL doesn't exist!");
        }
        return longUrlHead + shortUrlMap.get(shortUrl);
    }

    public void saveUrl(String shortUrl, String longUrl) {
        longUrlMap.put(longUrl, shortUrl);
        shortUrlMap.put(shortUrl, longUrl);
    }

    public String generateUrl(String adjustedUrl) {
        boolean isRandomUrlNotGenerated = true;
        String shortUrl = null;

        while (isRandomUrlNotGenerated) {
            shortUrl = "";
            for (int i = 0; i < shortUrlLength; i++) {
                int index = random.nextInt(0, ALLOWED_CHARS.length);
                shortUrl += ALLOWED_CHARS[index];
            }
            if (!shortUrlMap.containsKey(shortUrl)) {
                isRandomUrlNotGenerated = false;
            }
        }

        return shortUrl;
    }

    public String adjustUrl(String longUrl) {
        if (longUrl.substring(0, 7).equals("http://")) {
            longUrlHead = "http://";
            longUrl = longUrl.substring(7);
        } else if (longUrl.substring(0, 8).equals("https://")) {
            longUrlHead = "https://";
            longUrl = longUrl.substring(8);
        }
        if (longUrl.charAt(longUrl.length() - 1) == '/') {
            longUrl = longUrl.substring(0, longUrl.length() - 1);
        }
        return longUrl;
    }

    public Map<String, String> getLongUrlMap() {
        return longUrlMap;
    }

    public Map<String, String> getShortUrlMap() {
        return shortUrlMap;
    }

    public String getDomain() {
        return domain;
    }

    public Integer getShortUrlLength() {
        return shortUrlLength;
    }

    public String getLongUrlHead() {
        return longUrlHead;
    }
}
