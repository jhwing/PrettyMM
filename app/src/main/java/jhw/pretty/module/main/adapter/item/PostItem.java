package jhw.pretty.module.main.adapter.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jhw.pretty.R;
import jhw.pretty.bean.RespData;
import jhw.pretty.common.recyclerview.BaseItem;
import jhw.pretty.module.album.AlbumActivity;

/**
 * Created by jihongwen on 16/6/30.
 */

public class PostItem extends BaseItem<RespData.ResultsBean> implements View.OnClickListener {

    ImageView postImage;

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
        postImage.setOnClickListener(this);
    }

    @Override
    public void onBindView(RespData.ResultsBean resultsBean) {
        this.resultsBean = resultsBean;
        Glide.with(getContext()).load(resultsBean.url).into(postImage);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), AlbumActivity.class);
        intent.putExtra("bean", resultsBean);
        Bundle bundle = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) getContext(),
                        postImage, getContext().getString(R.string.transition_image)).toBundle();
        ActivityCompat.startActivity((Activity) getContext(), intent, bundle);
    }
}
