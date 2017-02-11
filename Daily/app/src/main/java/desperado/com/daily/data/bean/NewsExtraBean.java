package desperado.com.daily.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by desperado on 17-1-9.
 */
@Entity
public class NewsExtraBean implements Parcelable {


    /**
     * long_comments : 0
     * popularity : 161
     * short_comments : 19
     * comments : 19
     */
    @Property(nameInDb = "longComments")
    private int long_comments;
    @Property(nameInDb = "popularity")
    private int popularity;
    @Property(nameInDb = "shortComments")
    private int short_comments;
    @Property(nameInDb = "comments")
    private int comments;

    protected NewsExtraBean(Parcel in) {
        long_comments = in.readInt();
        popularity = in.readInt();
        short_comments = in.readInt();
        comments = in.readInt();
    }

    @Generated(hash = 2033362593)
    public NewsExtraBean(int long_comments, int popularity, int short_comments,
            int comments) {
        this.long_comments = long_comments;
        this.popularity = popularity;
        this.short_comments = short_comments;
        this.comments = comments;
    }

    @Generated(hash = 1680088156)
    public NewsExtraBean() {
    }

    public static final Creator<NewsExtraBean> CREATOR = new Creator<NewsExtraBean>() {
        @Override
        public NewsExtraBean createFromParcel(Parcel in) {
            return new NewsExtraBean(in);
        }

        @Override
        public NewsExtraBean[] newArray(int size) {
            return new NewsExtraBean[size];
        }
    };

    public int getLong_comments() {
        return long_comments;
    }

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(long_comments);
        dest.writeInt(popularity);
        dest.writeInt(short_comments);
        dest.writeInt(comments);
    }
}
