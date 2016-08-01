package com.daryl.nytimessearch.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daryl.nytimessearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleWithOnlyTextViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTitleInOnlyTextArticle) TextView tvTitleInOnlyTextArticle;

    public ArticleWithOnlyTextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTvTitleInOnlyTextArticle() {
        return tvTitleInOnlyTextArticle;
    }

    public void setTvTitleInOnlyTextArticle(TextView tvTitleInOnlyTextArticle) {
        this.tvTitleInOnlyTextArticle = tvTitleInOnlyTextArticle;
    }
}
