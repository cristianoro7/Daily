package desperado.com.daily.data.bean;

/**
 * Created by root on 17-4-3.
 */

public class NewsDetailAndExtraBean {

    private NewsDetailBean newsDetailBean;
    private NewsExtraBean newsExtraBean;

    public NewsDetailAndExtraBean(NewsDetailBean newsDetailBean, NewsExtraBean newsExtraBean) {
        this.newsDetailBean = newsDetailBean;
        this.newsExtraBean = newsExtraBean;
    }

    public NewsDetailBean getNewsDetailBean() {
        return newsDetailBean;
    }

    public void setNewsDetailBean(NewsDetailBean newsDetailBean) {
        this.newsDetailBean = newsDetailBean;
    }

    public NewsExtraBean getNewsExtraBean() {
        return newsExtraBean;
    }

    public void setNewsExtraBean(NewsExtraBean newsExtraBean) {
        this.newsExtraBean = newsExtraBean;
    }
}
