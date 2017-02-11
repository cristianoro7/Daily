package desperado.com.daily.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by desperado on 17-1-31.
 */
@Entity
public class ThemeListBean {

    private int color;
    private String thumbnail;
    private String description;
    private int id;
    private String name;

    @Generated(hash = 1570319247)
    public ThemeListBean(int color, String thumbnail, String description, int id,
            String name) {
        this.color = color;
        this.thumbnail = thumbnail;
        this.description = description;
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 28182613)
    public ThemeListBean() {
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
