package jhw.pretty.module.main.adapter.item;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jhw.pretty.R;
import jhw.pretty.bean.RespData;
import jhw.pretty.common.recyclerview.BaseItem;

/**
 * Created by jihongwen on 16/6/30.
 */

public class PostItem extends BaseItem<RespData.ResultsBean> {

    TextView createTime;
    ImageView postImage;

    public PostItem(ViewGroup parent) {
        super(parent);
    }

    @Override
    public int getRes() {
        return R.layout.view_post_item;
    }

    @Override
    public void onCreateView(View rootView) {
        createTime = (TextView) rootView.findViewById(R.id.createTime);
        postImage = (ImageView) rootView.findViewById(R.id.postImage);
    }

    @Override
    public void onBindView(RespData.ResultsBean resultsBean) {
        createTime.setText(resultsBean.createdAt);
        Glide.with(getContext()).load(resultsBean.url).into(postImage);
    }
}
