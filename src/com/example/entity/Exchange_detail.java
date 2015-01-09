package com.example.entity;

import java.io.Serializable;

/**
 * Created by LiesLee on 2015/1/9.
 * Email: LiesLee@foxmail.com
 */
public class Exchange_detail implements Serializable{
    private String epid;
    private String ep_name;
    private int ep_jifen;

    public String getEpid() {
        return epid;
    }

    public void setEpid(String epid) {
        this.epid = epid;
    }

    public String getEp_name() {
        return ep_name;
    }

    public void setEp_name(String ep_name) {
        this.ep_name = ep_name;
    }

    public int getEp_jifen() {
        return ep_jifen;
    }

    public void setEp_jifen(int ep_jifen) {
        this.ep_jifen = ep_jifen;
    }
}
