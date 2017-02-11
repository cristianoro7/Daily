package desperado.com.daily.presentation.utils;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ShortCommentsBean;

/**
 * Created by desperado on 17-1-2.
 * 日期和Bean等转化工具类
 */

public class ConvertUtil {

    private static final String TAG = ConvertUtil.class.getSimpleName();

    public static int dip2Px(Context context, float vaule) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (vaule * scale + 0.5f);
    }

    public static int px2Dip(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    public static String getBeforeDate(Context context, String date) {
        Calendar c = getCalBefore(date);
        return new SimpleDateFormat("MM月dd日").format(c.getTime()) + " " + getWeek(context, c);
    }

    public static String getDateBeforeUrl(String date) {
        Calendar calendar = getCalBefore(date);
        String datas = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        Log.d(TAG, "getDateBeforeUrl: " + datas);
        return datas;
    }

    public static List<LatestNewBean.StoriesBean> convertNewsBeforeBeanStoryToLatestNewsStory(
            List<NewsBeforeBean.StoriesBean> ns) {
        List<LatestNewBean.StoriesBean> list = new ArrayList<>();
        if (ns != null) {
            for (int i = 0; i < ns.size(); i++) {
                NewsBeforeBean.StoriesBean bean = ns.get(i);
                LatestNewBean.StoriesBean lBean = new LatestNewBean.StoriesBean();
                lBean.setId(bean.getId());
                lBean.setType(bean.getType());
                lBean.setTitle(bean.getTitle());
                lBean.setGa_prefix(bean.getGa_prefix());
                lBean.setImages(bean.getImages());
                lBean.setMultipic(bean.isMultipic());
                list.add(lBean);
            }
        }
        return list;
    }

    private static Calendar getCalBefore(String date) {
        Date nowDate = formatDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        int day = c.get(Calendar.DATE);
        c.add(Calendar.DAY_OF_YEAR, -1);
        return c;
    }

    private static String getWeek(Context context, Calendar cal) {
        String[] week = getWeekArray(context);
        int weekIndex = cal.get(cal.DAY_OF_WEEK);
        return week[weekIndex - 1];
    }

    private static String[] getWeekArray(Context context) {
        return context.getResources().getStringArray(R.array.weeks);
    }

    private static Date formatDate(String date) {
        int iDate = Integer.valueOf(date);
        int year = iDate / 10000;
        int month = (iDate % 10000) / 100 - 1;
        int day = iDate % 100;
        return new GregorianCalendar(year, month, day).getTime();
    }

    public static List<CommentsBean> convertLongCommentsBeansToCommentBeans(LongCommentsBean longCommentsBeen, int shortCommentsSize) {
        List<CommentsBean> list = null;
        if (longCommentsBeen != null) {
            list = new ArrayList<>();
            List<LongCommentsBean.CommentsBean> commentsBeanList = longCommentsBeen.getComments();
            CommentsBean commentsBean;
            for (int i = 0; i < commentsBeanList.size(); i++) {
                commentsBean = new CommentsBean();
                LongCommentsBean.CommentsBean bean = commentsBeanList.get(i);
                commentsBean.setAuthor(bean.getAuthor());
                commentsBean.setAvatar(bean.getAvatar());
                commentsBean.setContent(bean.getContent());
                commentsBean.setId(bean.getId());
                commentsBean.setLikes(String.valueOf(bean.getLikes()));
                commentsBean.setLongCommentSize(commentsBeanList.size());
                commentsBean.setShortCommentSize(shortCommentsSize);
                Log.d(TAG, "convertLongCommentsBeansToCommentBeans: " + shortCommentsSize);
                commentsBean.setReply_to(bean.getReply_to());
                commentsBean.setTime(convertMillToDate(bean.getTime()));
                list.add(commentsBean);
            }
        }
        return list;
    }

    public static List<CommentsBean> convertShortCommentsBeansToCommentsBeans(ShortCommentsBean shortCommentsBean) {
        List<CommentsBean> list = null;
        if (shortCommentsBean != null) {
            list = new ArrayList<>();
            List<ShortCommentsBean.CommentsBean> commentsBeanList = shortCommentsBean.getComments();
            CommentsBean commentsBean;
            for (int i = 0; i < commentsBeanList.size(); i++) {
                commentsBean = new CommentsBean();
                ShortCommentsBean.CommentsBean bean = commentsBeanList.get(i);
                commentsBean.setAuthor(bean.getAuthor());
                commentsBean.setAvatar(bean.getAvatar());
                commentsBean.setContent(bean.getContent());
                commentsBean.setId(bean.getId());
                commentsBean.setLikes(String.valueOf(bean.getLikes()));
                commentsBean.setReply_to(bean.getReply_to());
                commentsBean.setTime(convertMillToDate(bean.getTime()));
                list.add(commentsBean);
            }
        }
        return list;
    }

    private static String convertMillToDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd hh:mm");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time * 1000);
        Log.d(TAG, "convertMillToDate: " + time * 1000);
        Log.d(TAG, "convertMillToDate: " + format.format(c.getTime()));
        return format.format(c.getTime());
    }

}
