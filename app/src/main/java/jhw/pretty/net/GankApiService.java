package jhw.pretty.net;

import android.support.annotation.NonNull;

import jhw.pretty.bean.RespData;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jihongwen on 16/6/30.
 */

public class GankApiService implements GankApi {

    private static GankApi mApi;

    private static GankApiService instance;

    public static GankApiService getInstance() {
        if (instance == null) {
            instance = new GankApiService();
        }
        return instance;
    }

    private static GankApi getApi() {
        if (mApi == null) {
            mApi = initApi();
        }
        return mApi;
    }

    private static GankApi initApi() {
        String baseUrl = HOST;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(builder.build())
                .build();
        return retrofit.create(GankApi.class);
    }

    @NonNull
    private <T extends RespData> Observable.Transformer<T, T> getTransformer() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .flatMap(new Func1<T, Observable<T>>() {
                            @Override
                            public Observable<T> call(final T t) {
                                return flatResponse(t);
                            }
                        });
            }
        };
    }

    @NonNull
    private <T extends RespData> Observable<T> flatResponse(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                // TODO: 16/6/8 统一处理网络响应
                if (t == null || t.error) {
                    subscriber.onError(new RuntimeException("服务器返回error"));
                } else {
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<RespData> data(@Path("platform") String platform, @Path("count") String count, @Path("page") String page) {
        return getApi().data(platform, count, page).compose(this.getTransformer());
    }

    @Override
    public Observable<RespData> search(@Path("platform") String platform, @Path("count") String count, @Path("page") String page) {
        return getApi().search(platform, count, page).compose(this.getTransformer());
    }

    @Override
    public Observable<RespData> historyContent(@Path("count") String count, @Path("page") String page) {
        return getApi().historyContent(count, page).compose(this.getTransformer());
    }

    @Override
    public Observable<RespData> historyContentDay(@Path("data") String data) {
        return getApi().historyContentDay(data).compose(this.getTransformer());
    }

    @Override
    public Observable<RespData> history() {
        return getApi().history().compose(this.getTransformer());
    }

    @Override
    public Observable<RespData> add2gank() {
        return getApi().add2gank().compose(this.getTransformer());
    }
}
