package com.daryl.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daryl.nytimessearch.R;
import com.daryl.nytimessearch.models.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {

    @BindView(R.id.wvArticle)
    WebView wvArticle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ShareActionProvider miShareAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupWebview();
    }

    private void setupWebview() {
        Article article = (Article) getIntent().getSerializableExtra("article");
        wvArticle.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvArticle.loadUrl(article.getWebUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_article_activity, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share_article_activity);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_TEXT, wvArticle.getUrl());

        miShareAction.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);
    }
}
