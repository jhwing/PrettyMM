package jhw.pretty.module.main.adapter.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jhw.pretty.R;
import jhw.pretty.bean.RespData;
import jhw.pretty.common.recyclerview.BaseItem;
import jhw.pretty.module.album.AlbumActivity;
import jhw.pretty.module.article.ArticleActivity;
import jhw.pretty.widget.SecretTextView;

/**
 * Created by jihongwen on 16/6/30.
 */

public class PostItem extends BaseItem<RespData.ResultsBean> implements View.OnClickListener {

    ImageView postImage;
    SecretTextView content;

    RespData.ResultsBean resultsBean;

    public PostItem(ViewGroup parent) {
        super(parent);
    }

    @Override
    public int getRes() {
        return R.layout.view_post_item;
    }

    @Override
    public void onCreateView(View rootView) {
        postImage = (ImageView) rootView.findViewById(R.id.postImage);
        content = (SecretTextView) rootView.findViewById(R.id.content);
        postImage.setOnClickListener(this);
    }

    @Override
    public void onBindView(RespData.ResultsBean resultsBean) {
        this.resultsBean = resultsBean;
        content.setText(resultsBean.desc);
        if (!content.getIsVisible()) {
            content.show();
        }
        RespData.ResultsBean imageBean = resultsBean.imageBean;
        if (imageBean != null) {
            Glide.with(getContext()).load(imageBean.url).into(postImage);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ArticleActivity.class);
        resultsBean.imageUrl = resultsBean.imageBean.url;
        intent.putExtra("bean", resultsBean);
        intent.putExtra("url", resultsBean.url);
        Bundle bundle = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) getContext(),
                        postImage, getContext().getString(R.string.transition_image)).toBundle();
        ActivityCompat.startActivity((Activity) getContext(), intent, bundle);
    }
}
