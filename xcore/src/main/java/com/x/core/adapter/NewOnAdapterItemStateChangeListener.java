package com.x.core.adapter;

import android.view.View;


public interface NewOnAdapterItemStateChangeListener {
	void onStateChanged(NewAdapterItem item, View v, int... posIndex);
	
}