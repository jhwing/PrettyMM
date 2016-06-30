package jhw.pretty.common.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jihongwen on 16/5/25.
 */
public abstract class BaseItem<T> implements ItemViewInterface<T>, View.OnClickListener {

    private static final String TAG = BaseItem.class.getSimpleName();

    protected View rootView;

    protected LayoutInflater factory;

    protected int position;

    public BaseItem(ViewGroup parent) {
        factory = LayoutInflater.from(parent.getContext());
        rootView = factory.inflate(getRes(), parent, false);
        rootView.setOnClickListener(this);
    }

    public void onBindView(T t, int position) {
        this.position = position;
        onBindView(t);
    }

    public View getRootView() {
        return rootView;
    }

    public Context getContext() {
        return rootView.getContext();
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void onClick(View v) {
        // item 被点击
        //Toast.makeText(rootView.getContext(), "item 被点击了", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "item onClick");
    }

}
