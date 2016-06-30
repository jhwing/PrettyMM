package jhw.pretty.common.recyclerview;

import android.view.View;

/**
 * Created by jihongwen on 16/5/27.
 */
public interface ItemViewInterface<T> {

    int getRes();

    void onCreateView(View rootView);

    void onBindView(T t);
}
