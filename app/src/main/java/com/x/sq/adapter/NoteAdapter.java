package com.x.sq.adapter;

import android.content.Context;
import android.view.View;

import com.x.core.adapter.NewRecyclerViewAdapter;
import com.x.core.adapter.NewRecyclerViewHolder;
import com.x.sq.R;
import com.x.sq.holder.MainActHolder;
import com.x.sq.holder.MainListHolder;
import com.x.sq.model.ListItem;
import com.x.sq.model.MainItem;
import com.x.sq.model.StringItem;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class NoteAdapter extends NewRecyclerViewAdapter<NewRecyclerViewHolder> {
    public NoteAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onInitViewType() {
        addViewType(MainItem.class, R.layout.item_main_act);
    }

    @Override
    protected NewRecyclerViewHolder onCreateViewHolder(View view, Context ctx, int viewType) {
        return new MainActHolder(view, ctx);
    }

    @Override
    protected void onDestroy() {

    }
}
