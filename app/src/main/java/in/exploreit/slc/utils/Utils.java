package in.exploreit.slc.utils;

import android.content.Context;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;

public class Utils {
    public static final String TAG = "ExploreItLogTag";

    public static void openWebpage(Context context, String pageUrl) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(pageUrl));
    }
}
