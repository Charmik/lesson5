package com.example.untitled;

/**
 * Created with IntelliJ IDEA.
 * User: Charm
 * Date: 17.10.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<RssItem> objects;

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    MyAdapter(Context context, ArrayList<RssItem> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = lInflater.inflate(R.layout.item_list, parent, false);

        RssItem f = (RssItem) getItem(position);
        ((TextView) view.findViewById(R.id.date)).setText(f.getPubDate());

        ((TextView) view.findViewById(R.id.title)).setText(f.getTitle());
        ((TextView) view.findViewById(R.id.description)).setText(f.getDescription());
        return view;
    }
}