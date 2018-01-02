package com.x.sq.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.x.core.base.GlideApp;
import com.x.core.base.actionbar.ActionBarMenu;
import com.x.sq.R;

/**
 * http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg
 * Created by xudafeng on 2017/11/20.
 */

public class GlideDemoActivity extends SupperActivity {
    public static Intent createIntent(Context context) {
        return new Intent(context, GlideDemoActivity.class);
    }

    private static final String IMG_URL = "http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg";
    private static final String IMG_URL_GIF = "http://p1.pstatp.com/large/166200019850062839d3";
    @Override
    protected ActionBarMenu onActionBarCreate() {
        return new ActionBarMenu("Glide Demo");
    }

    private ImageView ivGlide;
    @Override
    protected int getContentView() {
        return R.layout.act_glide_demo_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivGlide = (ImageView) findViewById(R.id.id_act_glide_demo_iv);
    }

    public void loadImg(View view) {
        GlideApp.with(this)
//                .asBitmap()             //加载指定格式asGif
                .load(IMG_URL_GIF)
//                .skipMemoryCache(true) //表示禁用掉内存缓存策略，默认开启
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
//                .override(100)           //指定加载图片的大小，不至于内存浪费，一般不需要设定，glide会自动判定
                .into(ivGlide);
    }
}
