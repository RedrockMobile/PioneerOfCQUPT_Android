package com.mredrock.cypioneer.model;

import java.util.List;

/**
 * Created by simonla on 2016/6/15.
 * Have a good day.
 */
public class BBSDetailBean {

    /**
     * status : 200
     * info : success
     * data : {"lz":{"id":"1","title":"title","content":"adsf","time":"1465989116","name":"alld"},"reply":[{"content":"reply0","time":"1465989194","name":"alld"},{"content":"reply","time":"1465989233","name":"alld"},{"content":"reply2","time":"1465989233","name":"asvdn"},{"content":"huifu","time":"1465992318","name":"asvdn"}]}
     */

    private int status;
    private String info;
    /**
     * lz : {"id":"1","title":"title","content":"adsf","time":"1465989116","name":"alld"}
     * reply : [{"content":"reply0","time":"1465989194","name":"alld"},{"content":"reply","time":"1465989233","name":"alld"},{"content":"reply2","time":"1465989233","name":"asvdn"},{"content":"huifu","time":"1465992318","name":"asvdn"}]
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : title
         * content : adsf
         * time : 1465989116
         * name : alld
         */

        private LzBean lz;
        /**
         * content : reply0
         * time : 1465989194
         * name : alld
         */

        private List<ReplyBean> reply;

        public LzBean getLz() {
            return lz;
        }

        public void setLz(LzBean lz) {
            this.lz = lz;
        }

        public List<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(List<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class LzBean {
            private String id;
            private String title;
            private String content;
            private String time;
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ReplyBean {
            private String content;
            private String time;
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
