package desperado.com.daily.base.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by desperado on 17-1-1.
 */

public abstract class OnItemTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private GestureDetectorCompat mGestureDetectorCompat;

    public OnItemTouchListener(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(), new MyGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if(child != null) {
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(child);
                onItemClick(viewHolder, viewHolder.getAdapterPosition());
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }
    }

    public abstract void onItemClick(RecyclerView.ViewHolder viewHolder, int position);
}
