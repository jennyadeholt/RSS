package com.rss.client.android;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;


@EActivity(R.layout.main)
public class RSS extends Activity{

    @ViewById(R.id.rss_list)
    ListView rssList;

    @ViewById(R.id.rss_title)
    TextView title;

    @ViewById(R.id.rss_description)
    TextView description;

    @Bean
    ServerConnection serverConnection;

    @Bean
    RSSAdapter adapter;

    @AfterViews
    void bindAdapter() {
        rssList.setAdapter(adapter);
        RSSFeed rssFeed = serverConnection.getRssFeed(false);

        title.setText(rssFeed.getTitle());
        description.setText(rssFeed.getDescription());

    }

    @ItemClick
    void rssListItemClicked(RSSItem rssItem) {
        Log.d("RSS", rssItem.getLink());
    }








}
