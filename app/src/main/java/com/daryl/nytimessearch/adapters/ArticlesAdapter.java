package com.daryl.nytimessearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daryl.nytimessearch.R;
import com.daryl.nytimessearch.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView ivImage;
        public TextView tvTitle;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);

            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    private List<Article> mArticles;
    private Context mContext;

    public ArticlesAdapter(List<Article> articles, Context context) {
        this.mArticles = articles;
        this.mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View articleView = inflater.inflate(R.layout.item_article_result, parent, false);

        ViewHolder viewHolder = new ViewHolder(articleView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = mArticles.get(position);

        ImageView ivImage = holder.ivImage;
        String imageThumbnail = article.getThumbNail();
        if (imageThumbnail != null && !imageThumbnail.isEmpty()) {
            Picasso.with(getContext()).load(imageThumbnail).into(ivImage);
        }
        TextView tvTitle = holder.tvTitle;
        tvTitle.setText(article.getHeadline());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

}
