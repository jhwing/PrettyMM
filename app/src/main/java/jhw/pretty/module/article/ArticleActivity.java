package jhw.pretty.module.article;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jhw.pretty.R;
import jhw.pretty.bean.RespData;

/**
 * Created by jihongwen on 16/7/8.
 */

public class ArticleActivity extends AppCompatActivity {

    ImageView backdrop;
    WebView webView;
    Toolbar toolbar;

    RespData.ResultsBean bean;
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.webView);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        Intent intent = getIntent();
        if (intent != null) {
            bean = intent.getParcelableExtra("bean");
            Glide.with(this).load(bean.imageUrl).asBitmap().into(backdrop);
            url = intent.getStringExtra("url");
        }
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
