package com.mredrock.cypioneer.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.mredrock.cypioneer.R;

import java.util.Stack;

/**
 * Created by PinkD on 2016/6/14.
 * Double click to exit
 */
public class DelayClose {
    private static final String TAG = "DelayClose--->";
    private static long last = 0;
    private static Stack<Activity> activities = new Stack<>();

    public static void attachActivity(Activity activity) {
        activities.push(activity);
        PinkUtils.LogD(TAG, activity.toString().split("@")[0] + " attached");
    }

    public static void detachActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
            PinkUtils.LogD(TAG, activity.toString().split("@")[0] + " detached");
        }
    }

    public static void onBackPressed() {//poll = peek + remove
        long now = System.currentTimeMillis();
        if (now - last < 2000) {
            activities.pop().finish();
        } else {
            last = now;
            try {
                Toast.makeText(activities.peek(), R.string.press_again, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearStackExcept(Activity activity) {
        for (Activity tmp : activities) {
            if (!activity.equals(tmp)) {
                activities.remove(tmp);
                PinkUtils.LogD(TAG, tmp.toString().split("@")[0] + " removed");
                tmp.finish();
            }
        }
    }
}
