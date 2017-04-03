package desperado.com.daily.presentation.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by root on 17-4-3.
 */

public class PicassoHelper {

    public static void loadImageBySimplyWay(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }


}
