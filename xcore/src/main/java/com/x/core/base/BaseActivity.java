package com.x.core.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.x.core.R;
import com.x.core.base.actionbar.ActionBarMenu;
import com.x.core.base.actionbar.OnActionBarItemSelectedListener;
import com.x.core.base.actionbar.OnViewSizeConfirmed;

/**
 * Created by xudafeng on 2017/11/10.
 */

public abstract class BaseActivity extends FragmentActivity implements OnActionBarItemSelectedListener, OnViewSizeConfirmed{
    protected  final String tag = this.getClass().getName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGlobalView();
        setContentView(mGlobalView);
        initGoBack();
        calcViewSize(mGlobalView, this);
        if (actionBarView != null) {
            calcViewSize(actionBarView, new OnViewSizeConfirmed() {
                @Override
                public void onViewSizeConfirmed(View v, int width, int height) {
                    actionBarHeight = height;
                }
            });
        }
    }

    protected int activityWidth = 0;
    protected int activityHeight = 0;
    /** 获取activity的宽高 */
    @Override
    public void onViewSizeConfirmed(View v, int width, int height) {
        activityWidth = v.getWidth();
        activityHeight = v.getHeight();
    }

    /** 初始返回事件，并提供onGoBack()方法供子类实现具体的逻辑 */
    private void initGoBack() {
        View goBackView = getGoBackView();
        if (goBackView != null) {
            goBackView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGoBack();
                }
            });
        }
    }

    /** 返回的ResID */
    protected View getGoBackView() {
        ActionBarMenu bar = getActionBarMenu();
        if(bar != null){
            return bar.getLeftLayout();
        }
        return null;
    }

    /** 当点击返回按钮时调用 */
    protected void onGoBack() {
        this.finish();
    }

    /** 计算View的宽高 */
    protected final void calcViewSize(final View v, final OnViewSizeConfirmed listener) {
        ViewTreeObserver vto = v.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                v.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                listener.onViewSizeConfirmed(v, v.getWidth(), v.getHeight());
            }
        });
    }

    protected ViewGroup mGlobalView = null;
    ActionBarMenu mActionBar = null;
    private int actionBarHeight = 0;
    View actionBarView = null;
    protected void initGlobalView() {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(getContentView(), null);
        mActionBar = onActionBarCreate();
        if(mActionBar != null) {
            LinearLayout myLinearLayout = getLinearLayout();
            actionBarView = inflateActionBarView();
            actionBarHeight = actionBarView.getHeight();
            mActionBar.setViewAndListener(actionBarView, this);
            myLinearLayout.addView(actionBarView);

            ViewGroup.LayoutParams lp1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            myLinearLayout.addView(viewGroup, lp1);

            mGlobalView = myLinearLayout;
        }else {
            mGlobalView = viewGroup;
        }
    }

    private LinearLayout getLinearLayout() {
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        ll.setLayoutParams(lp);
        return ll;
    }

    protected View inflateActionBarView() {
        return LayoutInflater.from(this).inflate(R.layout.layout_actionbar, null);
    }

    /**本Activity对应的xml文件*/
    protected abstract int getContentView();

    /** Actionbar创建的时候,如果需要则返回Actionbar */
    protected ActionBarMenu onActionBarCreate() {
        return null;
    }

    /** ActionBar中的View的点击事件 */
    @Override
    public void onActionBarClick(View v) {

    }

    /** 获得Actionbar的高度 */
    protected final int getActionBarHeight() {
        return this.actionBarHeight;
    }

    /**返回 ActionBar实例*/
    protected final ActionBarMenu getActionBarMenu(){
        return this.mActionBar;
    }
}
