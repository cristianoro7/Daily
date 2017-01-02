package desperado.com.daily.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import desperado.com.daily.R;
import desperado.com.daily.utils.ConvertUtil;

/**
 * Created by desperado on 17-1-2.
 */

public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final String TAG = BannerView.class.getSimpleName();
    private static final int POINT_SELECTED_INCR = 2; //底部被选中的指示器增加的宽度
    private static final int POINT_SELECTED_WIDTH = 6; //底部指示器的宽度

    private BannerViewAdapter mAdapter;
    private ViewPager mVpViewPager;
    private Context mContext;
    private int mShowCount;
    private int mTotalCount = 100;
    private int mCurrentPosition = 0;
    private LinearLayout mLlIndicate;
    private int mPagePointWidth;
    private int mPagePointSelectedIncr = POINT_SELECTED_INCR;
    private boolean mIsTouching = false;

    private Timer mTimer = new Timer();
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!mIsTouching) {
                mCurrentPosition = (mCurrentPosition + 1) % mTotalCount;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentPosition == mTotalCount - 1) {
                            mVpViewPager.setCurrentItem(mShowCount - 1, true);
                        } else {
                            Log.d(TAG, "handleMessage: " + mCurrentPosition);
                            mVpViewPager.setCurrentItem(mCurrentPosition, true);
                        }
                        Log.d(TAG, "run: " + Thread.currentThread().getId());
                    }
                });
            } else {
                removeAllCallBack();
            }
        }
    };

    public BannerView(Context context) {
        super(context);
        this.mContext = context;
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_banner_view_content, null);
        this.mVpViewPager = (ViewPager) view.findViewById(R.id.banner_view_vp_view_pager);
        this.mLlIndicate = (LinearLayout) view.findViewById(R.id.banner_view_ll_indicate);
        mPagePointWidth = ConvertUtil.dip2Px(mContext, POINT_SELECTED_WIDTH);
        this.mVpViewPager.addOnPageChangeListener(this);
        mPagePointWidth = ConvertUtil.dip2Px(mContext, POINT_SELECTED_WIDTH);
        Log.d(TAG, "onFinishInflate: ");
        addView(view);
    }

    private void init() {
        Log.d(TAG, "init: ");
        mVpViewPager.setAdapter(null);
        mLlIndicate.removeAllViews();
        if (mAdapter.isEmpty()) {
            return;
        }

        int count = mAdapter.getCount();
        mShowCount = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View v = new View(mContext);
            if (mCurrentPosition == i) {
                v.setPressed(true);
                setIndicatorLayoutParams(v, mPagePointWidth + ConvertUtil.dip2Px(mContext, mPagePointSelectedIncr),
                        mPagePointWidth + ConvertUtil.dip2Px(mContext, mPagePointSelectedIncr), mPagePointWidth);
            } else {
                v.setPressed(false);
                setIndicatorLayoutParams(v, mPagePointWidth, mPagePointWidth, mPagePointWidth);
            }
            v.setBackgroundResource(R.drawable.banner_view_incator);
            mLlIndicate.addView(v);
        }
        mVpViewPager.setAdapter(new BannerViewPagerAdapter());
        mVpViewPager.setCurrentItem(0);
        initListener();
        mTimer.schedule(mTimerTask, 3000, 5000);
    }

    private void initListener() {
        mVpViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        mIsTouching = true;
                        mTimer.purge();
                        break;
                    case MotionEvent.ACTION_UP:
                        mIsTouching = false;
                        mTimerTask.run();
                        break;
                }
                return false;
            }
        });
    }

    private void setIndicatorLayoutParams(View view, int width, int height, int leftMargins) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(leftMargins, 0, 0, 0);
        view.setLayoutParams(params);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled: " + "position:" + position + ", positionOffset:" + positionOffset
                + ", positionOffsetPixels:" + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        int count = mLlIndicate.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = mLlIndicate.getChildAt(i);
            if (position % mShowCount == i) {
                v.setPressed(true);
                setIndicatorLayoutParams(v, mPagePointWidth + ConvertUtil.dip2Px(mContext, mPagePointSelectedIncr),
                        mPagePointWidth + ConvertUtil.dip2Px(mContext, mPagePointSelectedIncr), mPagePointWidth);
            } else {
                v.setPressed(false);
                setIndicatorLayoutParams(v, mPagePointWidth, mPagePointWidth, mPagePointWidth);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged: " + state);
    }

    public void setBannerViewAdapter(BannerViewAdapter adapter) {
        this.mAdapter = adapter;
        if (mAdapter != null) {
            init();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mCurrentPosition == mTotalCount - 1) {
                mVpViewPager.setCurrentItem(mShowCount - 1, true);
            } else {
                Log.d(TAG, "handleMessage: " + mCurrentPosition);
                mVpViewPager.setCurrentItem(mCurrentPosition, true);
            }
        }
    };

    public void removeAllCallBack() {
        mHandler.removeCallbacksAndMessages(this);
    }

    public interface BannerViewAdapter {
        boolean isEmpty();

        View getView(int position);

        int getCount();
    }

    private class BannerViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mTotalCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position %= mShowCount;
            View v = mAdapter.getView(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int position = mVpViewPager.getCurrentItem();
            if (position == 0) {
                position = mShowCount;
                mVpViewPager.setCurrentItem(position, true);
            } else if (position == mTotalCount - 1) {
                position = mShowCount - 1;
                mVpViewPager.setCurrentItem(position, true);
            }
        }
    }
}
