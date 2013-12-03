package com.example.untitled;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Charm
 * Date: 16.10.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class RssItem {
    private String title;
    private String description;
    private Date pubDate;
    private String link;

    public RssItem(String title, String description, Date pubDate, String link) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPubDate() {
        return String.format("%02d", this.pubDate.getDay()) + "." +
                String.format("%02d", this.pubDate.getMonth()) + "." +
                (this.pubDate.getYear() + 1900) + " " +
                String.format("%02d", this.pubDate.getHours()) + ":" +
                String.format("%02d", this.pubDate.getMinutes());
    }
}
