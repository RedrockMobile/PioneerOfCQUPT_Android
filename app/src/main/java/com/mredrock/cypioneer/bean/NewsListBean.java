package com.mredrock.cypioneer.bean;

import java.util.List;

/**
 * Created by simonla on 2016/6/17.
 * Have a good day.
 */
public class NewsListBean {

    /**
     * status : 200
     * info : success
     * data : [{"id":"16","title":"关于参加全市教育系统\u201c两学一做\u201d学习教育视频会议的通知","content":"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n","time":"2016-04-25 10:44:43"},{"id":"17","title":"关于召开全校\u201c两学一做\u201d学习教育工作部署会的通知","content":"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n","time":"2016-04-27 10:50:35"}]
     */

    private int status;
    private String info;
    /**
     * id : 16
     * title : 关于参加全市教育系统“两学一做”学习教育视频会议的通知
     * content : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n
     * time : 2016-04-25 10:44:43
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
        private String content;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
