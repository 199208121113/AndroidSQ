package com.x.sq.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.x.sq.R;
import com.x.sq.widget.CustomImgSpan;

/**
 * Created by xudafeng on 2017/11/10.
 */

public class HelpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_help, container, false);
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
