package com.mredrock.cypioneer.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.mredrock.cypioneer.R;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by PinkD on 2016/6/14.
 * Double click to exit
 */
public class DelayClose {
    private static final String TAG = "DelayClose--->";
    private static long last = 0;
    private static Queue<Activity> activities = new ArrayDeque<>();

    public static void attachActivity(Activity activity) {
        activities.add(activity);
    }

    public static void detachActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    public static void onBackPressed() {//poll = peek + remove
        long now = System.currentTimeMillis();
        if (now - last < 2000) {
            activities.poll().finish();
        } else {
            last = now;
            Toast.makeText(activities.peek(), R.string.press_again, Toast.LENGTH_SHORT).show();
        }
    }
}
