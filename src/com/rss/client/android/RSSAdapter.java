package com.rss.client.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class RSSAdapter extends BaseAdapter {

    List<RSSItem> rssItems;

    @RootContext
    Context context;

    @Bean
    ServerConnection serverConnection;

    @AfterInject
    public void afterInject(){
        rssItems =  serverConnection.getRssFeed(false).getRssItems();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RSSListItem personItemView;
        if (convertView == null) {
            personItemView = RSSListItem_.build(context);
        } else {
            personItemView = (RSSListItem) convertView;
        }

        personItemView.bind(getItem(position));

        return personItemView;
    }

    @Override
    public int getCount() {
        return rssItems.size();
    }

    @Override
    public RSSItem getItem(int position) {
        return rssItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}