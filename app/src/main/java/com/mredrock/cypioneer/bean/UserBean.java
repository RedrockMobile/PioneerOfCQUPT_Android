package com.mredrock.cypioneer.bean;

/**
 * Created by PinkD on 2016/6/16.
 * Javabean user
 */
public class UserBean {
    private String name;
    private String user_id;
    private int changed;

    public UserBean(String name) {
        this.name = name;
    }

    public UserBean(int changed, String user_id, String name) {
        this.changed = changed;
        this.user_id = user_id;
        this.name = name;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User:change " + changed + ", username '" + name + ", uid " + user_id;
    }
}
