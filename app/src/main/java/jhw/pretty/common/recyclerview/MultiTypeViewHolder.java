package jhw.pretty.common.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jihongwen on 16/6/8.
 */
public class MultiTypeViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseItem baseView;

    public MultiTypeViewHolder(BaseItem itemView) {
        super(itemView.getRootView());
        baseView = itemView;
        onCreateView(baseView.getRootView());
    }

    public void onCreateView(View rootView) {
        if (baseView != null) {
            baseView.onCreateView(rootView);
        }
    }

    public void onBindView(T t) {
        if (baseView != null) {
            baseView.onBindView(t, getLayoutPosition());
        }
    }
}
