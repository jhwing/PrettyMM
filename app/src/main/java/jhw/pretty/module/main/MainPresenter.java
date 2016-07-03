package jhw.pretty.module.main;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jhw.pretty.bean.RespData;
import jhw.pretty.net.GankApiService;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jihongwen on 16/6/30.
 */

public class MainPresenter implements MainContract.Presenter {

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    MainContract.IMainView iMainView;

    List<RespData.ResultsBean> beanList = new ArrayList<>();

    int mNp = 1;

    public MainPresenter(MainContract.IMainView mainView) {
        iMainView = mainView;
    }

    @Override
    public void unBind(Context context) {
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void initData(int postType) {
        load(0);
    }

    @Override
    public void loadData(int postType) {
        load(mNp);
    }

    private void load(final int np) {
        Subscription subscription = GankApiService.getInstance().data("福利", "20", np + "").subscribe(new Subscriber<RespData>() {
            @Override
            public void onCompleted() {
                if (np == 0) {
                    iMainView.resetRefresh();
                } else {
                    iMainView.resetLoadMore();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(iMainView.getViewContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(RespData respData) {
                if (respData.results != null) {
                    mNp++;
                    if (np == 0) {
                        beanList.clear();
                        beanList.addAll(respData.results);
                        iMainView.initListData(beanList);
                    } else {
                        beanList.addAll(respData.results);
                        iMainView.notifyDataSetChanged();
                    }
                }
            }
        });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void refreshData(int postType) {

    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public void useCache(boolean isUseCache) {

    }
}
