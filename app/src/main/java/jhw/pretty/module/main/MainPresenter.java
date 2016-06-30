package jhw.pretty.module.main;

import android.content.Context;
import android.widget.Toast;

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

    public MainPresenter(MainContract.IMainView mainView) {
        iMainView = mainView;
    }

    @Override
    public void unBind(Context context) {
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void initData(int postType) {
        loadData(postType);
    }

    @Override
    public void loadData(int postType) {
        Subscription subscription = GankApiService.getInstance().data("福利", "10", "1").subscribe(new Subscriber<RespData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(iMainView.getViewContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(RespData respData) {
                if (respData.results != null) {
                    iMainView.initListData(respData.results);
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
