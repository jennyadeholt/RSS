package com.rss.client.android;

public class RSSItem {

    private String publishedDate =  "Tue, 29 Jan 2013 02:20:49 -0800";
    private String title = "Aftonbladet";
    private String link = "http://aftonbladet.se";
    private String content = "";
    private String contentSnippet = "Aftonbladet är en skittidning som publiserar vad som helst...";


    public RSSItem(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContentSnippet() {
        return contentSnippet;
    }

    public void setContentSnippet(String contentSnippet) {
        this.contentSnippet = contentSnippet;
    }
}
