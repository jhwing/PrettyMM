package jhw.pretty.module.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jhw.pretty.R;
import jhw.pretty.base.activity.BaseAppCompatActivity;
import jhw.pretty.bean.RespData;

/**
 * Created by jihongwen on 16/7/1.
 */

public class AlbumActivity extends BaseAppCompatActivity {

    ImageView tagImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        tagImage = (ImageView) findViewById(R.id.tagImage);
        showImage();
    }

    private void showImage() {
        Intent intent = getIntent();
        RespData.ResultsBean resultsBean = intent.getParcelableExtra("bean");
        Glide.with(this).load(resultsBean.url).into(tagImage);
    }
}
