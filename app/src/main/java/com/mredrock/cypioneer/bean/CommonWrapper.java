package com.mredrock.cypioneer.bean;

import com.google.gson.internal.LinkedTreeMap;

/**
 * Created by PinkD on 2016/6/17.
 * wrapper
 */
public class CommonWrapper {
    private String info;
    private int status;
    private LinkedTreeMap data;

    public CommonWrapper(LinkedTreeMap data, String info, int status) {
        this.data = data;
        this.info = info;
        this.status = status;
    }

    public LinkedTreeMap getData() {
        return data;
    }

    public void setData(LinkedTreeMap data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommonWrapper{" +
                "data='" + data + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                '}';
    }
}
