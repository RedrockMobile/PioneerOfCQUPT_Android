package com.mredrock.cypioneer.bean;

import java.util.List;

/**
 * Created by simonla on 2016/6/17.
 * Have a good day.
 */
public class PhotoBean {

    /**
     * status : 200
     * info : success
     * data : [{"title":"学校召开\u201c两学一做\u201d学习教育动员部署会","link":"http://202.202.43.42/lxyz/index.php?m=Home&amp;c=Article&amp;a=index&amp;id=19","imgurl":" http://202.202.43.42/lxyz/Public/uploads/2016-06-07/575676790d7db.jpg"}]
     */

    private int status;
    private String info;
    /**
     * title : 学校召开“两学一做”学习教育动员部署会
     * link : http://202.202.43.42/lxyz/index.php?m=Home&amp;c=Article&amp;a=index&amp;id=19
     * imgurl :  http://202.202.43.42/lxyz/Public/uploads/2016-06-07/575676790d7db.jpg
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
        private String title;
        private String link;
        private String imgurl;

        public DataBean(String imgurl) {
            this.imgurl = imgurl;
        }

        public DataBean(String imgurl, String link, String title) {
            this.imgurl = imgurl;
            this.link = link;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}