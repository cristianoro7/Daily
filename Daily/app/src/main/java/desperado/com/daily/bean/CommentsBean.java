package desperado.com.daily.bean;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentsBean {

    private String author;
    private String content;
    private String avatar;
    private String time;
    private int id;
    private String likes;
    private LongCommentsBean.CommentsBean.ReplyToBean reply_to;
    private int longCommentSize;
    private int shortCommentSize;

    public int getLongCommentSize() {
        return longCommentSize;
    }

    public void setLongCommentSize(int longCommentSize) {
        this.longCommentSize = longCommentSize;
    }

    public int getShortCommentSize() {
        return shortCommentSize;
    }

    public void setShortCommentSize(int shortCommentSize) {
        this.shortCommentSize = shortCommentSize;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public LongCommentsBean.CommentsBean.ReplyToBean getReply_to() {
        return reply_to;
    }

    public void setReply_to(LongCommentsBean.CommentsBean.ReplyToBean reply_to) {
        this.reply_to = reply_to;
    }

    public static class ReplyToBean {
        /**
         * content : 第二个机灵抖的还是有逻辑问题，不该说忘了，应该说没喝过啊我也不知道
         * status : 0
         * id : 27275308
         * author : 2233155495
         */

        private String content;
        private int status;
        private int id;
        private String author;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
