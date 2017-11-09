package com.x.core.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ToastUtil {

	public static final void show(Context context, String msg) {
		show(context, msg, Toast.LENGTH_SHORT);
	}

	public static final void show(Context context, int resourceId, int mode) {
		String msg = context.getResources().getString(resourceId);
		show(context, msg, mode);
	}

	public static final void show(Context context, int resourceId) {
		show(context, resourceId, Toast.LENGTH_SHORT);
	}

	public static final void show(Context context, String msg, int mode) {
        if(context == null)
            return;
        if(mHandler != null) {
            showToast(context, msg, mode);
        }else{
            Toast.makeText(context.getApplicationContext(),msg,mode).show();
        }
	}

    private static Toast mToast=null;
    private static Handler mHandler = null;
    public static void init(Handler handler){
        mHandler=handler;
    }
    private static Runnable mRunnable = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    private static WeakReference<Context> curContext;
    private static void showToast(Context mContext, String text,int mode) {
        if(mContext == null)
            return;
        try {
            if(curContext == null || curContext.get() == null) {
                curContext = new WeakReference<>(mContext.getApplicationContext());
            }
            mHandler.removeCallbacks(mRunnable);
            if (mToast != null) {
                mToast.setText(text);
            }else {
                mToast = Toast.makeText(curContext.get(), text, Toast.LENGTH_SHORT);
            }
            int duration = 2000;
            if (mode != Toast.LENGTH_LONG ) {
                duration=3500;
            }
            mHandler.postDelayed(mRunnable,duration);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
