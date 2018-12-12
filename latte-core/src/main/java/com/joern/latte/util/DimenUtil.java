package com.joern.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.joern.latte.app.Latte;

/**
 * Created by Joern on 2018/12/12.
 */

public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;

    }
}
