package jhw.pretty.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jihongwen on 16/6/30.
 */

public class RespData implements Parcelable {

    /**
     * error : false
     * results : [{"_id":"577348a5421aa95e542023e8","createdAt":"2016-06-29T12:03:49.269Z","desc":"6.29","publishedAt":"2016-06-29T12:08:28.744Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg","used":true,"who":"daimajia"},{"_id":"5771e21c421aa931d274f24b","createdAt":"2016-06-28T10:34:04.375Z","desc":"6.28","publishedAt":"2016-06-28T11:33:25.276Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f5aqgzu2oej20rt15owo7.jpg","used":true,"who":"代码家"},{"_id":"57709843421aa95318978e88","createdAt":"2016-06-27T11:06:43.180Z","desc":"6.27","publishedAt":"2016-06-27T11:31:53.48Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034gw1f59lsn7wjnj20du0ku40c.jpg","used":true,"who":"代码家"},{"_id":"576caea6421aa90c875dc46b","createdAt":"2016-06-24T11:53:10.564Z","desc":"6.25","publishedAt":"2016-06-24T12:01:16.638Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f566a296rpj20lc0sggoj.jpg","used":true,"who":"代码家"},{"_id":"576b5d31421aa94f365b4fc0","createdAt":"2016-06-23T11:53:21.562Z","desc":"6.23","publishedAt":"2016-06-23T11:58:19.404Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f550nt5zklj20zk19rtf3.jpg","used":true,"who":"代码家"},{"_id":"5768b9f5421aa90462b22567","createdAt":"2016-06-21T11:52:21.177Z","desc":"6-21","publishedAt":"2016-06-21T12:00:17.657Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f52pe9xxn5j20dw0kidh6.jpg","used":true,"who":"代码家"},{"_id":"5760b303421aa940e70aa911","createdAt":"2016-06-15T09:44:35.925Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-15T11:55:46.992Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz6g6wppj20ms0xp13n.jpg","used":true,"who":"龙龙童鞋"},{"_id":"5760b2ee421aa940eb4e0f81","createdAt":"2016-06-15T09:44:14.179Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-16T12:19:00.930Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz7s830fj20gg0o00y5.jpg","used":true,"who":"龙龙童鞋"},{"_id":"5760b299421aa940eb4e0f80","createdAt":"2016-06-15T09:42:49.747Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-22T11:57:27.848Z","source":"web","type":"福利","url":"http://ww3.sinaimg.cn/mw690/81309c56jw1f4v6mic7r5j20m80wan5n.jpg","used":true,"who":"龙龙童鞋"},{"_id":"5760a606421aa940f1b54acf","createdAt":"2016-06-15T08:49:10.942Z","desc":"本田翼","publishedAt":"2016-06-17T12:04:39.386Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f4vmdn2f5nj20kq0rm755.jpg","used":true,"who":"代码家"}]
     */

    public boolean error;
    /**
     * _id : 577348a5421aa95e542023e8
     * createdAt : 2016-06-29T12:03:49.269Z
     * desc : 6.29
     * publishedAt : 2016-06-29T12:08:28.744Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg
     * used : true
     * who : daimajia
     */

    public List<ResultsBean> results;

    public static class ResultsBean implements Parcelable {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String content;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.content);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.content = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public RespData() {
    }

    protected RespData(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<RespData> CREATOR = new Parcelable.Creator<RespData>() {
        @Override
        public RespData createFromParcel(Parcel source) {
            return new RespData(source);
        }

        @Override
        public RespData[] newArray(int size) {
            return new RespData[size];
        }
    };
}
