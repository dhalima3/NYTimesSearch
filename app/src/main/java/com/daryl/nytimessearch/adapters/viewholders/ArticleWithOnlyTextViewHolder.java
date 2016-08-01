package com.daryl.nytimessearch.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daryl.nytimessearch.R;

public class ArticleWithOnlyTextViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitleInOnlyTextArticle;

    public ArticleWithOnlyTextViewHolder(View itemView) {
        super(itemView);
        tvTitleInOnlyTextArticle = (TextView) itemView.findViewById(R.id.tvTitleInOnlyTextArticle);
    }

    public TextView getTvTitleInOnlyTextArticle() {
        return tvTitleInOnlyTextArticle;
    }

    public void setTvTitleInOnlyTextArticle(TextView tvTitleInOnlyTextArticle) {
        this.tvTitleInOnlyTextArticle = tvTitleInOnlyTextArticle;
    }
}
