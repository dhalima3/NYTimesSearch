package com.daryl.nytimessearch.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.daryl.nytimessearch.models.Article;

import java.util.List;

public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public ArticleArrayAdapter(Context context, List<Article> articles) {
        super(context, android.R.layout.simple_list_item_1, articles);
    }
}
