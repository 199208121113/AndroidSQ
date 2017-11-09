package com.x.sq.model;

import java.util.List;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class ListItem {
    private List<StringItem> datas;

    public ListItem() {
    }

    public ListItem(List<StringItem> datas) {
        this.datas = datas;
    }

    public List<StringItem> getDatas() {
        return datas;
    }

    public void setDatas(List<StringItem> datas) {
        this.datas = datas;
    }
}
