package com.mredrock.cypioneer.model.bean;

/**
 * Created by PinkD on 2016/6/17.
 * wrapper
 */
public class CommonWrapper {
    private String info;
    private int status;
    private String data;

    public CommonWrapper(String data, String info, int status) {
        this.data = data;
        this.info = info;
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
}
