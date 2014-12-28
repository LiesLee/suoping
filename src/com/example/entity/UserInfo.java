package com.example.entity;

import com.example.util.StringUtils;

import java.io.Serializable;

/**
 * 用户信息
 * Created by LiesLee on 2014/12/28.
 * Email: LiesLee@foxmail.com
 */
public class UserInfo implements Serializable {
    /** 用户id **/
    private String id = StringUtils.EMPTY;
    /** 手机号码 **/
    private String username = StringUtils.EMPTY;
    /** 总积分 **/
    private float sum_earn;
    /** 当天积分 **/
    private float today_earn;
    /** 性别 0代表男性，1代表女性 **/
    private int sex;
    /** 邀请码 **/
    private int invite_no;
    /** 用户昵称 **/
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getSum_earn() {
        return sum_earn;
    }

    public void setSum_earn(float sum_earn) {
        this.sum_earn = sum_earn;
    }

    public float getToday_earn() {
        return today_earn;
    }

    public void setToday_earn(float today_earn) {
        this.today_earn = today_earn;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getInvite_no() {
        return invite_no;
    }

    public void setInvite_no(int invite_no) {
        this.invite_no = invite_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", sum_earn=" + sum_earn +
                ", today_earn=" + today_earn +
                ", sex=" + sex +
                ", invite_no=" + invite_no +
                ", name='" + name + '\'' +
                '}';
    }
}
