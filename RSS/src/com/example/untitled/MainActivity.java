package com.example.untitled;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    public static TextView textView;
    public static ListView lv;
    ArrayList<RssItem> data = new ArrayList<RssItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.GONE);
        lv = (ListView) findViewById(R.id.listView);

        String str = "http://news.rambler.ru/rss/scitech/";
        getXml xml = new getXml(str);

        xml.execute();


    }

    public class getXml extends AsyncTask<Void, Void, ArrayList<RssItem>> {
        String link;

        getXml(String link) {
            this.link = link;
        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> result) {
            super.onPostExecute(result);
            if (result.size() == 0) {
                MainActivity.textView.setVisibility(View.VISIBLE);
            }
            myAdapter adapter = new myAdapter(MainActivity.this, result);
            lv.setAdapter(adapter);
            final ArrayList<RssItem> finalData = result;
            final ArrayList<RssItem> finalData1 = result;
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                    RssItem rssItem = finalData.get(position);
                    Intent intent = new Intent(MainActivity.this, showNews.class);
                    intent.putExtra("link", finalData1.get(position).getLink());
                    startActivity(intent);
                }
            });
        }

        @Override
        protected ArrayList<RssItem> doInBackground(Void... params) {

            ArrayList<RssItem> rssItems = new ArrayList<RssItem>();
            try {
                URL url = new URL(link);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream input = conn.getInputStream();
                    DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dB = dBF.newDocumentBuilder();
                    Document document = dB.parse(input);
                    Element element = document.getDocumentElement();
                    NodeList nodeList = element.getElementsByTagName("item");
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Element entry = (Element) nodeList.item(i);
                        Element title = (Element) entry.getElementsByTagName("title").item(0);
                        Element description = (Element) entry.getElementsByTagName("description").item(0);
                        Element pubDate = (Element) entry.getElementsByTagName("pubDate").item(0);
                        Element link = (Element) entry.getElementsByTagName("link").item(0);
                        String strTitle = title.getFirstChild().getNodeValue();
                        String strDescription = description.getFirstChild().getNodeValue();
                        Date PubDate = new Date(pubDate.getFirstChild().getNodeValue());
                        String strLink = link.getFirstChild().getNodeValue();
                        RssItem rssItem = new RssItem(strTitle, strDescription, PubDate, strLink);
                        rssItems.add(rssItem);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rssItems;
        }
    }

}
