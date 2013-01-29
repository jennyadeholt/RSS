package com.rss.client.android;


import android.text.Html;
import android.text.Spanned;

import java.util.ArrayList;
import java.util.List;

public class RSSFeed {

    private ResponseData responseData;

    public static class ResponseData{
        private Feed feed;
    }

    public static class Feed {
        private List<RSSItem> entries;
        private String feedUrl;
        private String title;
        private String url;
        private String description;
    }

   public RSSFeed(){

   }

   public List<RSSItem> getRssItems(){
       List<RSSItem> rssFeed = new ArrayList<RSSItem>();

       if (responseData != null){
            if (responseData.feed != null){
                if (responseData.feed.entries != null){
                    rssFeed = responseData.feed.entries;
                }
            }
       }
       return rssFeed;
   }

    public String getTitle() {
        String title = "";
        if (responseData != null){
            if (responseData.feed != null){
                if (responseData.feed.entries != null){
                    title = responseData.feed.title;
                }
            }
        }

        return title;
    }

    public String getDescription() {
        String description = "";
        if (responseData != null){
            if (responseData.feed != null){
                if (responseData.feed.entries != null){
                    description = responseData.feed.description;
                }
            }
        }

        return description;
    }
}
