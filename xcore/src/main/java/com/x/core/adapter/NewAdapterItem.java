package com.x.core.adapter;

import android.view.View;


public class NewAdapterItem {
	
	private Object data;
	private Object state;
	private NewOnAdapterItemStateChangeListener onAdapterItemStateChangeListener = null;

	public NewAdapterItem(Object data, Object state) {
		if (data == null)
			throw new IllegalArgumentException("data is null");
		this.data = data;
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public Object getState() {
		return state;
	}

	public void notifyStateChanged(View v,int... posIndex) {
		NewOnAdapterItemStateChangeListener listener = getOnAdapterItemStateChangeListener();
		if (listener != null)
			listener.onStateChanged(this,v,posIndex);
	}

	public void setOnAdapterItemStateChangeListener(NewOnAdapterItemStateChangeListener onAdapterItemStateChangeListener) {
		this.onAdapterItemStateChangeListener = onAdapterItemStateChangeListener;
	}

	public NewOnAdapterItemStateChangeListener getOnAdapterItemStateChangeListener() {
		return onAdapterItemStateChangeListener;
	}
}