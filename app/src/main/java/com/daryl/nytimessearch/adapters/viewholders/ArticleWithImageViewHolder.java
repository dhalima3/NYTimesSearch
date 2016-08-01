package com.daryl.nytimessearch.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daryl.nytimessearch.R;

// Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
public class ArticleWithImageViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public ImageView ivImage;
    public TextView tvTitle;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ArticleWithImageViewHolder(View itemView) {
        super(itemView);

        ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitleInArticleWithImage);
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }
}
