package com.x.sq.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.x.core.adapter.NewRecyclerViewHolder;
import com.x.sq.R;
import com.x.sq.adapter.MainActAdapter;
import com.x.sq.model.ListItem;
import com.x.sq.model.StringItem;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class MainListHolder extends NewRecyclerViewHolder {
    private FamiliarRecyclerView familiarRecyclerViewiew;
    public MainListHolder(View rootView, Context ctx) {
        super(rootView, ctx);
    }

    @Override
    protected void onInitViews(View view) {
        familiarRecyclerViewiew = find(R.id.id_item_main_act_list);
    }

    @Override
    protected void onBindItem() {
        if(getItem().getData() instanceof ListItem) {
            ListItem itemData = (ListItem) getItem().getData();
            MainActAdapter adapter = new MainActAdapter(getMyContext());
            familiarRecyclerViewiew.setAdapter(adapter);
            for (int i = 0; i < itemData.getDatas().size(); i++) {
                adapter.addItem(itemData.getDatas().get(i), null);
            }
            adapter.notifyDataSetChanged();
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
