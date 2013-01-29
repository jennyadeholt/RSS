package com.rss.client.android;

import com.googlecode.androidannotations.annotations.rest.Accept;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.googlecode.androidannotations.api.rest.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Rest(value = "https://ajax.googleapis.com/ajax/services/feed/load")
@Accept(MediaType.APPLICATION_JSON)
public interface RSSClient {

    @Get("?v=1.0&num={numbers}&q={rssFeed}")
    RSSFeed getFeed(int numbers, String rssFeed);

    RestTemplate getRestTemplate();
}

