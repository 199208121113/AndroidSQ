package com.x.av.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.x.av.R;
import com.x.av.model.AVGuideItem;
import com.x.core.adapter.NewRecyclerViewHolder;

/**
 * Created by xudafeng on 2018/1/2.
 */

public class AVGuideHolder extends NewRecyclerViewHolder {
    public AVGuideHolder(View rootView, Context ctx) {
        super(rootView, ctx);
    }

    private TextView tvTitle;
    @Override
    protected void onInitViews(View view) {
        tvTitle = find(R.id.item_av_guide_item_title);
    }

    @Override
    protected void onBindItem() {
        if(getItem().getData() instanceof AVGuideItem) {
            AVGuideItem itemData = (AVGuideItem) getItem().getData();
            tvTitle.setText(itemData.getItemDesc());
        }
    }

    @Override
    protected void onRecycleItem() {
        onBindItem();
    }

    @Override
    protected void onRefreshView() {

    }

    @Override
    protected void onDestroy() {

    }
}
