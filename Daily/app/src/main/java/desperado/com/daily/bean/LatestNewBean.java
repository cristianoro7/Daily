package desperado.com.daily.bean;

import java.util.List;

/**
 * Created by desperado on 17-1-2.
 */

public class LatestNewBean {

    /**
     * date : 20170102
     * stories : [{"images":["http://pic3.zhimg.com/1063e1edfd04d58bec92b127b1d308ae.jpg"],"type":0,"id":9108482,"ga_prefix":"010216","title":"关于讨价还价这事，可能男人更「作死」，女人更理性"},{"images":["http://pic1.zhimg.com/3e8e703880e1d397ce7e150c2235fcf4.jpg"],"type":0,"id":9116258,"ga_prefix":"010215","title":"日本的贫富差距为什么这么小？"},{"images":["http://pic3.zhimg.com/ff88d6085a9057d00b2cdd98edaa644a.jpg"],"type":0,"id":9111257,"ga_prefix":"010214","title":"如何在水下憋气超过 3 分钟？"},{"images":["http://pic1.zhimg.com/c2f2ce80f1befb899458ae4a50fc1724.jpg"],"type":0,"id":9102306,"ga_prefix":"010213","title":"怀孕「傻三年」，不会真的变傻，可能是你的生活不一样了"},{"images":["http://pic2.zhimg.com/24fa75ec72eeb3bc214f77c3c892c5d9.jpg"],"type":0,"id":9114177,"ga_prefix":"010212","title":"大误 · 武功强不强，还是要看排名"},{"images":["http://pic1.zhimg.com/9bfba8fe5b4630ebb1c47aa1933d3bec.jpg"],"type":0,"id":9115342,"ga_prefix":"010211","title":"仪表盘显示 92km/h，实际车速可能才刚到 80km/h"},{"title":"经典力学中有哪些违反直觉的现象 / 实验？","ga_prefix":"010210","images":["http://pic4.zhimg.com/3025369c5890d370ac87cbadcd124f2b.jpg"],"multipic":true,"type":0,"id":9114444},{"images":["http://pic4.zhimg.com/5033962be7d96bdd8f079a70884f63cf.jpg"],"type":0,"id":9099300,"ga_prefix":"010209","title":"如何跟老代码友好相处？"},{"images":["http://pic4.zhimg.com/0e4850eb11a82ed1f24ecd15d01bd433.jpg"],"type":0,"id":9114993,"ga_prefix":"010208","title":"跳槽太多，可能在简历筛选环节就被刷掉了"},{"images":["http://pic4.zhimg.com/f065a3c1873f89951380652cd54d457f.jpg"],"type":0,"id":9114923,"ga_prefix":"010207","title":"北京地铁坐了很久，这些事还真不知道"},{"images":["http://pic4.zhimg.com/5ee4b1195f057b324c2ac04d1809a1b3.jpg"],"type":0,"id":9115158,"ga_prefix":"010207","title":"种种迹象表明，2017 会比 2016 更不平凡"},{"title":"2016 年度盘点 · 值得一说的年度最佳 app","ga_prefix":"010207","images":["http://pic2.zhimg.com/2906f58b92572450151b3c709f37cad9.jpg"],"multipic":true,"type":0,"id":9108467},{"images":["http://pic3.zhimg.com/9410545235f6e4a2c1d86adb46c37b6a.jpg"],"type":0,"id":9111374,"ga_prefix":"010206","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/29ae7db7b61112d6eedafd51757ee51c.jpg","type":0,"id":9102306,"ga_prefix":"010213","title":"怀孕「傻三年」，不会真的变傻，可能是你的生活不一样了"},{"image":"http://pic3.zhimg.com/7115539f3eae7e8555e9714de273b9b2.jpg","type":0,"id":9115158,"ga_prefix":"010207","title":"种种迹象表明，2017 会比 2016 更不平凡"},{"image":"http://pic2.zhimg.com/65085a54170b5b4d095d3f5b94e75e79.jpg","type":0,"id":9108482,"ga_prefix":"010216","title":"关于讨价还价这事，可能男人更「作死」，女人更理性"},{"image":"http://pic4.zhimg.com/3cbf12f33f92439ae8fbda3dd7ae2d67.jpg","type":0,"id":9108467,"ga_prefix":"010207","title":"2016 年度盘点 · 值得一说的年度最佳 app"},{"image":"http://pic1.zhimg.com/9b677bec48e3be115d3b3fa462aeea58.jpg","type":0,"id":9114251,"ga_prefix":"010117","title":"知乎好问题 · 除旧换新的方式有哪些讲究？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic3.zhimg.com/1063e1edfd04d58bec92b127b1d308ae.jpg"]
         * type : 0
         * id : 9108482
         * ga_prefix : 010216
         * title : 关于讨价还价这事，可能男人更「作死」，女人更理性
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic1.zhimg.com/29ae7db7b61112d6eedafd51757ee51c.jpg
         * type : 0
         * id : 9102306
         * ga_prefix : 010213
         * title : 怀孕「傻三年」，不会真的变傻，可能是你的生活不一样了
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
