package com.mredrock.cypioneer.cfg;

import android.content.Context;

import com.mredrock.cypioneer.model.bean.UserBean;

import java.io.File;
import java.util.Stack;

/**
 * Created by PinkD on 2016/6/15.
 * Configs
 */
public class Config {
    public static File cacheDir;

    public static void init(Context context) {
        cacheDir = context.getExternalCacheDir();
    }

    public static UserBean user;

    public enum PLAY {
        up,
        down,
        left,
        right,
        a,
        b;

        public static Stack<PLAY> getTargetStack() {
            Stack<PLAY> stack = new Stack<>();
            stack.push(up);
            stack.push(up);
            stack.push(down);
            stack.push(down);
            stack.push(left);
            stack.push(right);
            stack.push(left);
            stack.push(right);
            stack.push(b);
            stack.push(a);
            stack.push(b);
            stack.push(a);
            return stack;
        }
    }
}
