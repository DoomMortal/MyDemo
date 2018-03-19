package com.demo.mydemo;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Miguel Catalan Bañuls
 */
public class SearchAdapter extends ArrayAdapter<SearchArticle> implements Filterable {

    private static final String TAG = "SearchAdapter";
    private Activity mContext;
    private int viewResourceId;
    private ArrayList<SearchArticle> web;

    public SearchAdapter(Activity context, int viewResourceId, ArrayList<SearchArticle> web) {
        super(context, viewResourceId, viewResourceId, web);
        this.mContext = context;
        this.viewResourceId = viewResourceId;
        this.web = web;
    }

    @Override
    public int getCount() {
        return web.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView");

        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        SearchArticle article = web.get(position);
        if (article != null) {
            TextView customerNameLabel = v.findViewById(R.id.customerNameLabel);
            if (customerNameLabel != null) {
                customerNameLabel.setText(String.valueOf(article.getArticleName()));
            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}