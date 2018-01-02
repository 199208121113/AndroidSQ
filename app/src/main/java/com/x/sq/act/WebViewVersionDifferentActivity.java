package com.x.sq.act;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * WebView区分,Android4.0及以上设备
 *          Android WebView 实现的Framework层大致可以分为三段Android4.0系列/Android4.1-4.3系列/Android4.4及其以上系列
 *          1).Android4.4以下（不包含4.4）采用WebKit内核
 *          2).Android4.4及其以上采用google chromium内核
 *  注：Android4.0默认开启硬件加速，因此webView的渲染是基于硬件渲染的
 * Created by xudafeng on 2017/11/20.
 */

public class WebViewVersionDifferentActivity extends SupperActivity{
    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
