package com.daryl.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.daryl.nytimessearch.R;
import com.daryl.nytimessearch.adapters.ArticleArrayAdapter;
import com.daryl.nytimessearch.fragments.FilterSearchDialogFragment;
import com.daryl.nytimessearch.fragments.FilterSearchDialogFragment.FilterSearchDialogListener;
import com.daryl.nytimessearch.models.Article;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity implements FilterSearchDialogListener {

    private GridView gvResults;
    private ArrayList<Article> articles;
    private ArticleArrayAdapter adapter;
    private String beginDate;
    private String sortOrder;
    private boolean isArts;
    private boolean isFashionAndStyle;
    private boolean isSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_activity, menu);
        setUpSearchView(menu);
        setUpFilterFragment(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFinishFilterSearchDialog(String beginDate, String sortOrder, boolean isArts,
                                           boolean isFashionAndStyle, boolean isSports) {
        this.beginDate = beginDate;
        this.sortOrder = sortOrder;
        this.isArts = isArts;
        this.isFashionAndStyle = isFashionAndStyle;
        this.isSports = isSports;
    }

    public void setupViews() {
        gvResults = (GridView) findViewById(R.id.gvResults);
        articles = new ArrayList<>();
        adapter = new ArticleArrayAdapter(this, articles);
        gvResults.setAdapter(adapter);

        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                Article article = articles.get(position);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });
    }

    private void setUpSearchView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchArticleWith(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setUpFilterFragment(Menu menu) {
        MenuItem settingsItem = menu.findItem(R.id.action_settings);
        settingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showFilterSearchDialog();
                return true;
            }
        });
    }

    private void showFilterSearchDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FilterSearchDialogFragment filterSearchDialogFragment = FilterSearchDialogFragment.newInstance(
                getString(R.string.filter_search_fragment_title), beginDate, sortOrder, isArts,
                isFashionAndStyle, isSports);
        filterSearchDialogFragment.show(fragmentManager, "fragment_filter_search");
    }

    public void searchArticleWith(String query) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        RequestParams params = new RequestParams();
        params.put("api-key", "7c0da1f2f8ad45e387570d038ed0aab5");
        params.put("page", 0);
        params.put("q", query);
        if (beginDate != null && !beginDate.isEmpty()) {
            params.put("begin_date", beginDate);
        }
        if (sortOrder != null) {
            params.put("sort", sortOrder.toLowerCase());
        }
        if (!newsDeskParameter().isEmpty()) {
            params.put("fq", newsDeskParameter());
        }

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", "API response: " + response.toString());
                JSONArray articleJsonResults = null;

                try {
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
                    adapter.addAll(Article.fromJSONArray(articleJsonResults));
                    Log.d("DEBUG", articles.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private String newsDeskParameter() {
        StringBuilder newsDeskParam = new StringBuilder("news_desk:(");
        if (!isArts && !isFashionAndStyle && !isSports) {
            return "";
        }
        if (isArts) {
            newsDeskParam.append("\"Arts\"");
        }
        if (isFashionAndStyle) {
            if (newsDeskParam.length() > 11) {
                newsDeskParam.append(",");
            }
            newsDeskParam.append("\"Fashion & Style\"");
        }
        if (isSports) {
            if (newsDeskParam.length() > 11) {
                newsDeskParam.append(",");
            }
            newsDeskParam.append("\"Sports\"");
        }
        newsDeskParam.append(")");
        return newsDeskParam.toString();
    }
}
