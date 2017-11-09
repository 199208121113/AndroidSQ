package com.x.core.base;

import android.app.Application;

import com.x.core.util.LogUtil;

/**
 * Created by xudafeng on 2017/11/9.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.setDebug(true);
    }

}
