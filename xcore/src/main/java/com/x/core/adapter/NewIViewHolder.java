package com.x.core.adapter;

import android.view.View;

public interface NewIViewHolder {

	void setItem(NewAdapterItem item);

	NewAdapterItem getItem();

	void bindItem();

	void refreshView();

	View getRootView();

	void initViews();

	void recycleItem();

	void destroy();
}
