package jhw.pretty.module.main.adapter;

import android.view.ViewGroup;

import java.util.List;

import jhw.pretty.bean.RespData;
import jhw.pretty.common.recyclerview.BaseItem;
import jhw.pretty.common.recyclerview.MultiTypeListAdapter;
import jhw.pretty.module.main.adapter.item.ItemFactory;

/**
 * Created by jihongwen on 16/6/30.
 */

public class MainListAdapter extends MultiTypeListAdapter<RespData.ResultsBean> {

    List<RespData.ResultsBean> beanList;

    public void setBeanList(List<RespData.ResultsBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public RespData.ResultsBean getItem(int viewType, int position) {
        return beanList.get(position);
    }

    @Override
    public BaseItem<?> createItemView(ViewGroup parent, int viewType) {
        return ItemFactory.createItemView(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
