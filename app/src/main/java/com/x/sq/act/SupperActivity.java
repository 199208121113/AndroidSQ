package com.x.sq.act;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.x.core.base.BaseActivity;
import com.x.core.base.actionbar.ActionBarMenu;
import com.x.core.util.ScreenUtil;
import com.x.sq.R;

/**
 * Created by xudafeng on 2017/11/10.
 */

public abstract class SupperActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarMenu bar = getActionBarMenu();
        handActionBarMenu(bar, this);
    }

    private void handActionBarMenu(ActionBarMenu bar, Context ctx) {
        if (bar == null)
            return;
        if (bar.getIcon() <= 0) {
            ImageView iv = bar.getIconView();
            if (iv != null) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.back_arrow_ffffff);
                final int dp4 = ScreenUtil.dip2px(ctx, 11);
                final int dp5 = ScreenUtil.dip2px(ctx, 15);
                iv.setPadding(dp5, dp4, dp5, dp4);
            }
        }
        bar.getViewGroup().setBackgroundColor(0xff529bff);
        bar.getTitleView().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
    }
}
