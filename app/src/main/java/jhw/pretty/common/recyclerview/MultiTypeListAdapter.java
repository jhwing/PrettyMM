package jhw.pretty.common.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by jihongwen on 16/5/25.
 */
public abstract class MultiTypeListAdapter<T> extends RecyclerView.Adapter<MultiTypeViewHolder> {

    public MultiTypeListAdapter() {
    }

    @Override
    public MultiTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultiTypeViewHolder<>(createItemView(parent, viewType));
    }

    @Override
    public void onBindViewHolder(MultiTypeViewHolder holder, int position) {
        holder.onBindView(getItem(getItemViewType(position), position));
    }

    public abstract T getItem(int viewType, int position);

    public abstract BaseItem<?> createItemView(ViewGroup parent, int viewType);

}
