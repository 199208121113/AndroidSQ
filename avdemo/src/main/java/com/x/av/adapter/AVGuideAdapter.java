package com.x.av.adapter;

import android.content.Context;
import android.view.View;

import com.x.av.R;
import com.x.av.holder.AVGuideHolder;
import com.x.av.model.AVGuideItem;
import com.x.core.adapter.NewRecyclerViewAdapter;

/**
 * Created by xudafeng on 2018/1/2.
 */

public class AVGuideAdapter extends NewRecyclerViewAdapter<AVGuideHolder> {
    public AVGuideAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onInitViewType() {
        addViewType(AVGuideItem.class, R.layout.item_av_guide_layout);
    }

    @Override
    protected AVGuideHolder onCreateViewHolder(View view, Context ctx, int viewType) {
        return new AVGuideHolder(view, ctx);
    }

    @Override
    protected void onDestroy() {

    }
}
