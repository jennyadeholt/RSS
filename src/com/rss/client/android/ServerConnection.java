package com.rss.client.android;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.googlecode.androidannotations.api.Scope;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@EBean(scope = Scope.Singleton)
public class ServerConnection {

    private static final String TAG = "RSSFeed";
    private RSSFeed rssFeed;


    public RSSFeed getRssFeed(boolean update)  {

        if (rssFeed == null || update){
            String url = "https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=20&q=http://www.aftonbladet.se/rss.xml";

            HttpURLConnection urlConnection = getConnection(url);

            if (urlConnection != null) {
                try {
                    JsonReader reader = new JsonReader(
                            new InputStreamReader(urlConnection.getInputStream()));

                    RSSFeed feed = new Gson().fromJson(reader, RSSFeed.class);
                    if (feed != null) {
                        rssFeed = feed;
                    }
                    urlConnection.disconnect();
                } catch (JsonIOException e) {
                    Log.d(TAG, "RoadFactory -  JsonIOException", e);
                } catch (JsonSyntaxException e) {
                    Log.d(TAG, "RoadFactory -  JsonSyntaxException", e);
                } catch (IOException e) {
                    Log.d(TAG ,"RoadFactory -  IOException", e);
                }
            }

        }




        return rssFeed;
    }


    /**
     * @return The {@link javax.net.ssl.HttpsURLConnection}
     */
    public static HttpURLConnection getConnection(String urlString) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            Log.d("", "AccountUtil#getConnection MalformedURLException", e);
        } catch (IOException e) {
            Log.d("", "AccountUtil#getConnection IOException", e);
        }

        return urlConnection;
    }
}
