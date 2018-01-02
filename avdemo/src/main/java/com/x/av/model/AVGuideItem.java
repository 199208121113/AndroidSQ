package com.x.av.model;

/**
 * Created by xudafeng on 2018/1/2.
 */

public class AVGuideItem {
    private String itemDesc;
    private int itemId;

    public AVGuideItem() {
    }

    public AVGuideItem(String itemDesc, int itemId) {
        this.itemDesc = itemDesc;
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
