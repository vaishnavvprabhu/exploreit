package in.exploreit.slc.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.browser.customtabs.CustomTabsIntent;

import com.google.android.material.snackbar.Snackbar;

public class Utils {
    public static final String TAG = "ExploreItLogTag";

    public static void openWebpage(Context context, String pageUrl, View view) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        try {
            Log.d(TAG, "openWebpage: " + pageUrl);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(pageUrl));
        } catch (Exception e) {
            Log.d(TAG, "openWebpage: " + e.getMessage());
            snackBar(view, "Please visit \"exploreit.in\" in your browser.");
        }
    }

    public static void snackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("DISMISS", btn -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }
}
