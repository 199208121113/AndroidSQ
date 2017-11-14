package com.x.sq.model;

import java.io.Serializable;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class MainItem implements Serializable {
    private static final long serialVersionUID = 1L;

    public MainItem() {
    }

    public MainItem(int itemId, String str) {
        this.itemId = itemId;
        this.str = str;
    }

    private int itemId;
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
