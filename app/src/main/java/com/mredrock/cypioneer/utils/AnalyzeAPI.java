package com.mredrock.cypioneer.utils;

import android.util.Log;

import com.mredrock.cypioneer.model.bean.CarouselFigure;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 临时的工具类
 * Created by xushuzhan on 2016/6/16.
 */
public class AnalyzeAPI implements Runnable {
    public static final String TAG = "AnalyzeAPI";
    private String API;
    AnalyzeAPIListener listener;
    ArrayList<CarouselFigure> myPicture;

    public interface AnalyzeAPIListener {
        void onSuccess() throws MalformedURLException;
        void onFailed();
}

    public AnalyzeAPI(final String API, AnalyzeAPIListener listener, ArrayList<CarouselFigure> myPicture) {
        this.API = API;
        this.listener = listener;
        this.myPicture=myPicture;
        Thread thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(API);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10 * 1000);
            connection.setReadTimeout(10 * 1000);
            InputStream in = connection.getInputStream();
            //读取输入流的内容
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            parseJsonWithJsonObject(response.toString());

        } catch (Exception e) {
            if(listener!=null){
                Log.d(TAG, "获得Json: 网络好像有点问题 (ಥ _ ಥ)· " + e.getMessage());
                listener.onFailed();

            }

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public void parseJsonWithJsonObject(String JsonData) {
        //解析Json
        try {
            JSONObject object = new JSONObject(JsonData);
            JSONArray picyureJsonArray=object.getJSONArray("data");
            for (int i = 0; i < picyureJsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = picyureJsonArray.getJSONObject(i);
                    CarouselFigure carouselFigure = new CarouselFigure();
                        carouselFigure.setImgurl(jsonObject.getString("imgurl"));
                        Log.d(TAG, "parseJsonWithJsonObject: "+jsonObject.getString("imgurl"));
                        carouselFigure.setLink(jsonObject.getString("link"));
                        Log.d(TAG, "parseJsonWithJsonObject: "+jsonObject.getString("link"));
                        carouselFigure.setTitle(jsonObject.getString("title"));
                        Log.d(TAG, "parseJsonWithJsonObject: "+jsonObject.getString("title"));

                        myPicture.add(carouselFigure);
                }catch (Exception e){
                    Log.d(TAG, "parseJsonWithJsonObject: 解析有点问题哟");
                }
            }
            if (listener != null) {
                listener.onSuccess();
            }

        } catch (Exception e) {
            if(listener!=null){
                listener.onFailed();
            }
            Log.d(TAG, "解析Json: " + e.getMessage());
        }
    }
}
