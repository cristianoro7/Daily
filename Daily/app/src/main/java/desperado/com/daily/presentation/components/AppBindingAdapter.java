package desperado.com.daily.presentation.components;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by desperado on 16-12-31.
 */

public class AppBindingAdapter {

    private static final String TAG = AppBindingAdapter.class.getSimpleName();

    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, String src) {
        imageView.setImageDrawable(null);
        Picasso.with(imageView.getContext()).load(src).into(imageView);
        Log.d(TAG, "loadImage: url:" + src);
    }

    @BindingAdapter({"load_data"})
    public static void loadBody(WebView webView, String body) {
        webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
    }
}
