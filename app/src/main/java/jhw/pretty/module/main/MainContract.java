package jhw.pretty.module.main;

import java.util.List;

import jhw.pretty.base.IPresenter;
import jhw.pretty.base.IView;
import jhw.pretty.bean.RespData;

/**
 * Created by jihongwen on 16/6/30.
 */

public interface MainContract {

    interface IMainView extends IView {

        void initListData(List<RespData.ResultsBean> beanList);

        void notifyDataSetChanged();

        void showEmptyView(boolean show);

        void resetRefresh();

        void refresh();

        void resetLoadMore();

        void showLoadingView();

        void hideLoadingView();
    }

    interface Presenter extends IPresenter {

        void initData(int postType);

        void loadData(int postType);

        void refreshData(int postType);

        int getPageSize();

        void useCache(boolean isUseCache);
    }

}
