package com.x.sq.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Created by xudafeng on 2017/11/13.
 */

public class CustomImgSpan extends ImageSpan{
    private float lineSpace;
    public CustomImgSpan(Context context, int resourceId, float lineSpace) {
        super(context, resourceId);
        this.lineSpace = lineSpace;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        int transY = bottom - drawable.getBounds().bottom - (int)lineSpace;
        canvas.translate(x, transY);
        drawable.draw(canvas);
        canvas.restore();
    }
}
