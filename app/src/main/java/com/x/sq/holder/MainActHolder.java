package com.x.sq.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.x.core.adapter.NewRecyclerViewHolder;
import com.x.sq.R;
import com.x.sq.model.MainItem;

public class MainActHolder extends NewRecyclerViewHolder {
    private TextView tvTitle;
    public MainActHolder(View rootView, Context ctx) {
        super(rootView, ctx);
    }

    @Override
    protected void onInitViews(View view) {
        tvTitle = find(R.id.id_item_main_act_title);
    }

    @Override
    protected void onBindItem() {
        if(getItem().getData() instanceof MainItem) {
            MainItem itemData = (MainItem) getItem().getData();
            tvTitle.setText(itemData.getStr());
        }
    }

    @Override
    protected void onRecycleItem() {

    }

    @Override
    protected void onRefreshView() {

    }

    @Override
    protected void onDestroy() {

    }
}
