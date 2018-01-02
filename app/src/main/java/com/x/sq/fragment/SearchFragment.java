package com.x.sq.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.x.core.base.BaseFragment;
import com.x.core.util.ScreenUtil;
import com.x.sq.R;
import com.x.sq.widget.ExpandableSpanTextView;

/**
 * Created by xudafeng on 2017/11/10.
 */

public class SearchFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.fg_search;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExpandableSpanTextView tvTitle = (ExpandableSpanTextView) view.findViewById(R.id.id_fg_search_title);
        tvTitle.setLineSpace(ScreenUtil.dip2px(getActivity(), 10));
        tvTitle.setText("单行文本水平触摸滑动效果 通过EditText实现TextView单行长文本水平滑动效果  - 多行文本折叠展开 自定义布局View实现多行文本折叠和展开 1.概述经常在APP中能看到有引用文章或大段博文的内容，他们的展示样式也有点儿意思，默认是折叠的，当你点击文章之后它会自动展开");
    }
}
