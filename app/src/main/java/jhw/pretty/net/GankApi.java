package jhw.pretty.net;

import jhw.pretty.bean.RespData;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jihongwen on 16/6/30.
 */

public interface GankApi {

    String HOST = "http://gank.io/api/";

    /**
     * @param type  Android 福利 IOS all
     * @param count
     * @param page
     * @return
     */
    @GET("data/{type}/{count}/{page}")
    Observable<RespData> data(@Path("type") String type, @Path("count") String count, @Path("page") String page);

    @GET("search/query/listview/category/{type}/count/{count}/page/{page}")
    Observable<RespData> search(@Path("type") String type, @Path("count") String count, @Path("page") String page);

    @GET("history/content/{count}/{page}")
    Observable<RespData> historyContent(@Path("count") String count, @Path("page") String page);

    @GET("history/content/day/{data}")
    Observable<RespData> historyContentDay(@Path("data") String data);

    @GET("day/history")
    Observable<RespData> history();

    @POST("add2gank")
    Observable<RespData> add2gank();

}
