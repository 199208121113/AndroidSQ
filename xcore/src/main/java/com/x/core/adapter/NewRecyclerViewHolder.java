package com.x.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by root on 16-1-28.
 */
public abstract class NewRecyclerViewHolder extends RecyclerView.ViewHolder implements NewIViewHolder {
    protected final String TAG = this.getClass().getSimpleName();
    protected NewAdapterItem item = null;
    private View rootView = null;
    private final Context context;
    private int posIndex;

    public int getPosIndex() {
        return posIndex;
    }

    public void setPosIndex(int posIndex) {
        this.posIndex = posIndex;
    }

    public NewRecyclerViewHolder(View rootView, Context ctx) {
        super(rootView);
        this.rootView = rootView;
        this.context = ctx;
    }

    protected abstract void onInitViews(View view);

    protected abstract void onBindItem();

    protected abstract void onRecycleItem();

    protected abstract void onRefreshView();

    protected abstract void onDestroy();

    @Override
    public void setItem(NewAdapterItem item) {
        if (item == null)
            return;
        boolean changed = isChangedForCurrentEntity(item);
        if (!changed) {
            refreshView();
            return;
        }
        if (this.item != null) {
            recycleItem();
        }
        this.item = item;
        bindItem();
    }

    protected boolean isChangedForCurrentEntity(NewAdapterItem newItem){
        return this.item != newItem;
    }

    @Override
    public final NewAdapterItem getItem() {
        return this.item;
    }

    @Override
    public final void bindItem() {
        onBindItem();
    }

    @Override
    public final void refreshView() {
        onRefreshView();
    }

    @Override
    public final View getRootView() {
        return this.rootView;
    }

    @Override
    public final void initViews() {
        onInitViews(getRootView());
    }

    @Override
    public final void recycleItem() {
        onRecycleItem();
    }

    @Override
    public final void destroy() {
        onDestroy();
    }

    @SuppressWarnings("unchecked")
    protected final <V extends View> V find(int id) {
        return (V) this.rootView.findViewById(id);
    }

    public final Context getMyContext() {
        return context;
    }
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public NewRecyclerViewHolder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
