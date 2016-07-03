package jhw.pretty.common.loadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jhw.pretty.R;

/**
 * Created by jihongwen on 16/6/30.
 */

public class FootView {

    public static View getLayoutView(Context context, ViewGroup rootView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.foot_view, rootView, false);
    }
}
