package desperado.com.daily.databinding.components;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by desperado on 16-12-31.
 */

public class WelcomeBindingAdapter {

    private static final String TAG = WelcomeBindingAdapter.class.getSimpleName();

    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, String src) {
        Picasso.with(imageView.getContext()).load(src).into(imageView);
        Log.d(TAG, "loadImage: url:" + src);
    }
}
