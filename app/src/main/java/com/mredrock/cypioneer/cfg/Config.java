package com.mredrock.cypioneer.cfg;

import android.content.Context;

import java.io.File;

/**
 * Created by PinkD on 2016/6/15.
 * Configs
 */
public class Config {
    public static File cacheDir;

    public static void init(Context context) {
        cacheDir = context.getExternalCacheDir();
    }

    public static String username;
}
