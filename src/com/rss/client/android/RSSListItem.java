package com.rss.client.android;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@EViewGroup(R.layout.rss_list_item)
public class RSSListItem extends LinearLayout{

    @ViewById(R.id.list_item_time)
    TextView time;

    @ViewById(R.id.list_item_title)
    TextView title;


    @ViewById(R.id.list_item_link)
    TextView link;


    @ViewById(R.id.list_item_html)
    WebView html;


    public RSSListItem(Context context) {
        super(context);
    }

    public void bind(RSSItem item){
        time.setText(getTime(item.getPublishedDate()));
        title.setText(item.getTitle());

        link.setText(item.getLink());

        if (!TextUtils.isEmpty(item.getContent())){
            html.setVisibility(View.VISIBLE);
            String summary = "<html><body>" + item.getContent() + "</body></html>";

            html.loadData(summary, "text/html; charset=UTF-8", null);
        } else {
            html.setVisibility(View.GONE);
        }
    }

    @Background
    private String getTime(String publishedDate){
        Date date = new Date();

        try {
            SimpleDateFormat datetimeFormatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.UK);
            date = datetimeFormatter.parse(publishedDate.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return DateFormat.getDateTimeInstance().format(date) + " (" + getTimeAgo(date) + ")";
    }

    private String getTimeAgo(Date date){
        Long seconds = (System.currentTimeMillis() - date.getTime()) / 1000;

        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 3) {
            return String.valueOf(days) + " dagar sedan";
        } else if (hours > 0) {
            return String.valueOf(hours) + " timmar sedan";
        } else if (minutes > 0) {
            return String.valueOf(minutes) + " minuter sedan";
        } else {
            return String.valueOf(seconds) + " sekunder sedan";
        }

    }


}
