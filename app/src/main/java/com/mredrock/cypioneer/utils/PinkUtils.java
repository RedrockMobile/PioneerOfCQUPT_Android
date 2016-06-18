package com.mredrock.cypioneer.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;

/**
 * Created by PinkD on 2016/4/1.
 * Utils
 */
public class PinkUtils {
    public static final String UNKNOWN_ERROR = "An unknown error occurred!";
    public static final String NETWORK_ERROR = "Network error!";

    public static final int NETTYPE_ERROR = 0x00;
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    /**
     * @param url url
     * @return filename like 123abc.jpg
     */
    public static String urlToFileName(String url) {
        String tmp = url.split("\\?")[0];
        String[] file_path = tmp.split("/");
        return file_path[file_path.length - 2] + file_path[file_path.length - 1];
    }

    /**
     * @param inputStream inputStream
     * @return jsonObject
     */
    public static JSONObject responseToJSONObject(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String tmp;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuilder.append(tmp).append("\n");
            }
            return new JSONObject(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param inputStream inputStream
     * @return String content
     */

    public static String stringFromStream(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String tmp;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuilder.append(tmp).append("\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * cut number to *.00
     *
     * @param number double number
     * @return number like 3.14
     */
    private static String numberToString(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(number);
    }

    /**
     * @param len file length
     * @return String file length with appropriate unit
     */
    public static String byteToLarger(long len) {
        double result = len;
        if (result == 0) {
            return "0k";
        } else if (result < 1024) {
            return "不足1k";
        } else if (result < 1048576) {
            result /= 1024;
            return numberToString(result) + "kb";
        } else if (result < 1073741824) {
            result /= 1048576;
            return numberToString(result) + "mb";
        } else if (result < 1099511627776L) {
            result /= 1073741824;
            return numberToString(result) + "gb";
        } else if (result < 1125899906842624L) {
            result /= 1099511627776L;
            return numberToString(result) + "tb";
        } else {
            return "Do you have enough room to store it?";
        }
    }

    public static int getInt(JSONObject tmp, String name) {
        try {
            return tmp.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getString(JSONObject tmp, String name) {
        try {
            return tmp.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getInt(JSONObject tmp, String name, int def) {
        try {
            return tmp.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return def;
        }
    }

    public static String getString(JSONObject tmp, String name, String def) {
        try {
            return tmp.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return def;
        }
    }

    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!extraInfo.isEmpty()) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    public static String getFileType(String path) {
        String str[] = path.split("\\.");
        return str[str.length - 1];
    }

    public static byte[] getTargetGifByte(Context context, String name) throws IOException {
        byte[] buffer = new byte[1024];
        InputStream inputStream = getTargetGifInputStream(context, name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (inputStream.read(buffer) != -1) {
            byteArrayOutputStream.write(buffer);
        }
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static Movie getTargetGifMovie(Context context, String name) throws IOException {
        InputStream inputStream = getTargetGifInputStream(context, name);
        Movie movie = Movie.decodeStream(inputStream);
        inputStream.close();
        return movie;
    }

    public static InputStream getTargetGifInputStream(Context context, String name) throws IOException {
        AssetManager assetManager = context.getAssets();
        return assetManager.open(name);
    }
}
