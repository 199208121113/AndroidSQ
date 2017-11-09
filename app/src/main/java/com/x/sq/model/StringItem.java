package com.x.sq.model;

import java.io.Serializable;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class StringItem implements Serializable {
    private static final long serialVersionUID = 1L;

    public StringItem() {
    }

    public StringItem(String str) {
        this.str = str;
    }

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
