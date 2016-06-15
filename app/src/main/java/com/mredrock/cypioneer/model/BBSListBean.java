package com.mredrock.cypioneer.model;

import java.util.List;

/**
 * Created by simonla on 2016/6/15.
 * Have a good day.
 */
public class BBSListBean {

    /**
     * status : 200
     * info : success
     * data : [{"id":"8","title":"title2","user_id":"1","content":"adsf4","father_id":"0","time":"1465992353"},{"id":"5","title":"title2","user_id":"1","content":"adsf4","father_id":"0","time":"1465992264"},{"id":"1","title":"title","user_id":"2","content":"adsf","father_id":"0","time":"1465989116"}]
     */

    private int status;
    private String info;
    /**
     * id : 8
     * title : title2
     * user_id : 1
     * content : adsf4
     * father_id : 0
     * time : 1465992353
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String user_id;
        private String content;
        private String father_id;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFather_id() {
            return father_id;
        }

        public void setFather_id(String father_id) {
            this.father_id = father_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
