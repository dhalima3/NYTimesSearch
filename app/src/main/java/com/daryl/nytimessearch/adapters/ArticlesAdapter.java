package com.daryl.nytimessearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daryl.nytimessearch.R;
import com.daryl.nytimessearch.adapters.viewholders.ArticleWithImageViewHolder;
import com.daryl.nytimessearch.adapters.viewholders.ArticleWithOnlyTextViewHolder;
import com.daryl.nytimessearch.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ARTICLE_WITH_JUST_TEXT = 0;
    private final int ARTICLE_WITH_IMAGE = 1;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case ARTICLE_WITH_JUST_TEXT:
                View viewArticleWithJustText = inflater.inflate(R.layout.
                        layout_article_with_only_text_viewholder, viewGroup, false);
                viewHolder = new ArticleWithOnlyTextViewHolder(viewArticleWithJustText);
                break;
            case ARTICLE_WITH_IMAGE:
                View viewArticleWithImage = inflater.inflate(R.layout.
                        layout_article_with_image_viewholder, viewGroup, false);
                viewHolder = new ArticleWithImageViewHolder(viewArticleWithImage);
                break;
            default:
                viewArticleWithJustText = inflater.inflate(R.layout.
                        layout_article_with_only_text_viewholder, viewGroup, false);
                viewHolder = new ArticleWithOnlyTextViewHolder(viewArticleWithJustText);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ARTICLE_WITH_JUST_TEXT:
                ArticleWithOnlyTextViewHolder articleWithOnlyTextViewHolder =
                        (ArticleWithOnlyTextViewHolder) holder;
                configureArticleWithJustTextViewHolder(articleWithOnlyTextViewHolder, position);
                break;
            case ARTICLE_WITH_IMAGE:
                ArticleWithImageViewHolder articleWithImageViewHolder =
                        (ArticleWithImageViewHolder) holder;
                configureArticleWithImageViewHolder(articleWithImageViewHolder, position);
                break;
            default:
                articleWithOnlyTextViewHolder = (ArticleWithOnlyTextViewHolder) holder;
                configureArticleWithJustTextViewHolder(articleWithOnlyTextViewHolder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mArticles.get(position).getThumbNail() != null &&
                !mArticles.get(position).getThumbNail().isEmpty()) {
            return ARTICLE_WITH_IMAGE;
        } else if (!mArticles.get(position).getHeadline().isEmpty()){
            return ARTICLE_WITH_JUST_TEXT;
        }
        return -1;
    }

    private void configureArticleWithJustTextViewHolder(ArticleWithOnlyTextViewHolder viewHolder,
                                                        int position) {
        Article article = mArticles.get(position);
        if (article.getHeadline() != null) {
            viewHolder.getTvTitleInOnlyTextArticle().setText(article.getHeadline());
        }
    }

    private void configureArticleWithImageViewHolder(ArticleWithImageViewHolder viewHolder,
                                                     int position) {
        Article article = mArticles.get(position);
        String thumbNail = article.getThumbNail();
        if (thumbNail != null && !thumbNail.isEmpty() &&
                article.getHeadline() != null) {
            viewHolder.getTvTitle().setText(article.getHeadline());
            Picasso.with(getContext()).load(thumbNail).into(viewHolder.getIvImage());
        }
    }
}
