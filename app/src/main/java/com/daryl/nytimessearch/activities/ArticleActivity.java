package com.daryl.nytimessearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daryl.nytimessearch.R;
import com.daryl.nytimessearch.models.Article;

public class ArticleActivity extends AppCompatActivity {

    private WebView wvArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupWebview();
    }

    private void setupWebview() {
        wvArticle = (WebView) findViewById(R.id.wvArticle);

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

}
