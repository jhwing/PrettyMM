package jhw.pretty.module.main.adapter.item;

import android.view.ViewGroup;

import jhw.pretty.common.recyclerview.BaseItem;

/**
 * Created by jihongwen on 16/6/30.
 */

public class ItemFactory {

    public static BaseItem createItemView(ViewGroup parent, int viewType) {
        return new PostItem(parent);
    }

}
