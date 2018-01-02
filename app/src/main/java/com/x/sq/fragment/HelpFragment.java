package com.x.sq.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.x.core.base.BaseFragment;
import com.x.sq.R;

/**
 * Created by xudafeng on 2017/11/10.
 */

public class HelpFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.fg_help;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView) view.findViewById(R.id.id_fg_help_tv);

        final String txt = "this is help fgx";
        SpannableString spannableString = new SpannableString(txt);
        ImageSpan imgSpan = new ImageSpan(getActivity(), R.mipmap.icon_author_down, 2);

        spannableString.setSpan(imgSpan, txt.length()-1, txt.length(), DynamicDrawableSpan.ALIGN_BASELINE);
        tv.setText(spannableString);
    }
}
