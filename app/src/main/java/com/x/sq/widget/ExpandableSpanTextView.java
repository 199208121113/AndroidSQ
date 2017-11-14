package com.x.sq.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.x.core.util.ScreenUtil;
import com.x.sq.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Shuai on 2015/10/17.
 */
public class ExpandableSpanTextView extends AppCompatTextView {

    private OnHighlightTextListener listener;

    public void setOnHighlightTextListener(OnHighlightTextListener listener) {
        this.listener = listener;
    }

    public OnHighlightTextListener getOnHighlightTextListener () {
        return listener;
    }

    private static final String EXPANDABLE = "全文";
    private static final String COLLAPSE = "收起";
    private static final String ELLIPSIS = "...";
    private CharSequence originalText;
    private CharSequence trimmedText;
    private int expandTextColor;
    private boolean trim = true;
    private int defaultLineCount = 4;
    private BufferType bufferType;
    boolean onUrlOutsideClick = true;
    boolean isExpandable = false;
    boolean linkHit;
    private int estStyle;
    private float lineSpace;

    public float getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(float lineSpace) {
        this.lineSpace = lineSpace;
    }

    public boolean isTrim() {
        return trim;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    public ExpandableSpanTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ExpandableSpanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ExpandableSpanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context ctx, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.ExpandableSpanTextView, defStyleAttr, 0);
        defaultLineCount = a.getInteger(R.styleable.ExpandableSpanTextView_trimLines, 4);
        isExpandable = a.getBoolean(R.styleable.ExpandableSpanTextView_setExpandable, false);
        expandTextColor = a.getColor(R.styleable.ExpandableSpanTextView_expandTextColor, 0xFF529BFF);
        estStyle = a.getInt(R.styleable.ExpandableSpanTextView_estStyle, 0);
        a.recycle();
        setMovementMethod(new LocalLinkMovementMethod());
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    private void collapseOrExpandable() {
        trim = !trim;
        setDisplayText();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        linkHit = false;

        boolean res = super.onTouchEvent(event);

        if (onUrlOutsideClick)
            return linkHit;
        return res;

    }
    private void setDisplayText() {
        super.setText(getDisplayableText(),this.bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isExpandable) {
            originalText = getOriginalText(text);
        } else {
            originalText = text;
        }
        trimmedText = getTrimText(text);
        this.bufferType = type;
        setDisplayText();
    }

    private CharSequence getOriginalText(CharSequence text) {
        final int canvasWidth = getCanvasWidth();
        if(canvasWidth == 0){
            return text;
        }
        if(TextUtils.isEmpty(text))
            return "";

        if (getLineCountByOriginalText(text) <= defaultLineCount) {
            return text;
        }

        if (!isExpandable) {
            return text;
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String str = text + COLLAPSE;
        builder.append(str);
        final int expLen = COLLAPSE.length();
        final int strLen = str.length();
        builder.setSpan(clickableSpan, strLen - expLen, strLen, 0);
        if(estStyle == 1) {
            ImageSpan imgSpan = new CustomImgSpan(getContext(), R.mipmap.icon_author_up, getLineSpace());
            builder.setSpan(imgSpan, strLen - expLen, strLen, DynamicDrawableSpan.ALIGN_BASELINE);
        }
        return builder;
    }

    private CharSequence getTrimText(CharSequence text) {
        final int canvasWidth = getCanvasWidth();
        if(canvasWidth == 0){
            return text;
        }
        if(TextUtils.isEmpty(text))
            return "";
        if (getLineCountByOriginalText(text) <= defaultLineCount) {
            return text;
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (isExpandable) {
//            String str = getTrimTextNoDrawable() + ELLIPSIS + EXPANDABLE;
            String str = getTrimTextNoDrawable() + ELLIPSIS + EXPANDABLE;
            builder.append(str);
            final int expLen = EXPANDABLE.length();
            final int strLen = str.length();
            builder.setSpan(clickableSpan, strLen - expLen, strLen, 0);
            if(estStyle == 1) {
                ImageSpan imgSpan = new CustomImgSpan(getContext(),R.mipmap.icon_author_down, getLineSpace());
                builder.setSpan(imgSpan, strLen - expLen, strLen, DynamicDrawableSpan.ALIGN_BASELINE);
            }
        } else {
            builder.append(getTrimTextNoDrawable() + ELLIPSIS);
        }
        return builder;
    }

    private String getTrimTextNoDrawable() {
        final int canvasWidth = getCanvasWidth();
        Paint textPaint = getPaint();
        String tempStr = ELLIPSIS + EXPANDABLE;
        float lastDrawableWidth = textPaint.measureText(tempStr);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(originalText)) {
            List<String> paragraphList = createShowableList(originalText.toString());
            int i = 0;
            for (String str : paragraphList){
                int startIndex = 0;
                while (i < defaultLineCount) {
                    int drawWidth = canvasWidth;
                    if (i == defaultLineCount-1) {
                        drawWidth -= lastDrawableWidth;
                    }
                    int receivedLength = textPaint.breakText(str,startIndex,str.length(),true,drawWidth,null);
                    if(receivedLength == 0) {
                        break;
                    }
                    String ss = str.substring(startIndex,startIndex+receivedLength);
                    startIndex+=receivedLength;
                    sb.append(ss);
                    if(i < defaultLineCount-1){
                        sb.append("\n");
                    }
                    i++;
                    if(startIndex >= str.length() -1){
                        break ;
                    }
                }
                if(i>=defaultLineCount){
                    break;
                }
            }

        }
        return sb.toString();
    }

    // 获得原文的行数
    private int getLineCountByOriginalText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        final int canvasWidth = getCanvasWidth();
        if(canvasWidth <= 0){
            return 0;
        }
        Paint textPaint = getPaint();

        int lineCount = 0;
        List<String> paragraphList = createShowableList(text.toString());
        for (String str : paragraphList){
            int startIndex = 0;
            while (true) {
                int receivedLength = textPaint.breakText(str,startIndex,str.length(),true,canvasWidth,null);
                if(receivedLength == 0) {
                    break ;
                }
                startIndex+=receivedLength;
                lineCount++;
                if(startIndex == str.length() -1){
                    break ;
                }
            }
        }
        return lineCount;
    }

    private volatile int currentCanvasWidth = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        if(currentCanvasWidth == 0 && canvas != null){
            try {
                currentCanvasWidth = canvas.getWidth()-this.getPaddingLeft()-this.getPaddingRight();
                if(currentCanvasWidth == 0){
                    currentCanvasWidth = ScreenUtil.getDisplay(getContext()).getWidth();
                }
                super.setText(getText());
            } catch (Exception e) {
                //ignore
            }
            return;
        }
        super.onDraw(canvas);
    }

    private int getCanvasWidth(){
        return currentCanvasWidth;
    }


    String p_token = "##p#";
    private ArrayList<String> createShowableList(String mChapterContent){
        if (mChapterContent == null || mChapterContent.trim().length() == 0) {
            mChapterContent = " ";
        }
        ArrayList<String> mShowableList = new ArrayList<>();
        mChapterContent = mChapterContent.replaceAll("[(\n)?\r]", p_token);
        mChapterContent = mChapterContent.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "");
        mChapterContent = mChapterContent.replaceAll("</([^>]*)>", "");// 结束标签
        String[] txtArray = mChapterContent.split(p_token);
        for (String txtP : txtArray) {
            mShowableList.add(txtP);
        }
        return mShowableList;
    }

    public static class LocalLinkMovementMethod extends LinkMovementMethod {

        static LocalLinkMovementMethod sInstance;

        public static LocalLinkMovementMethod getInstance() {
            if (sInstance == null)
                sInstance = new LocalLinkMovementMethod();

            return sInstance;
        }

        @Override
        public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP ||
                    action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);

                ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);

                if (link.length != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(widget);
                    } else if (action == MotionEvent.ACTION_DOWN) {
                        Selection.setSelection(buffer,
                                buffer.getSpanStart(link[0]),
                                buffer.getSpanEnd(link[0]));
                    }

                    if (widget instanceof ExpandableSpanTextView) {
                        ((ExpandableSpanTextView) widget).linkHit = true;
                    }
                    return true;
                } else {
                    Selection.removeSelection(buffer);
                    Touch.onTouchEvent(widget, buffer, event);
                    return false;
                }
            }
            return Touch.onTouchEvent(widget, buffer, event);
        }
    }

    private ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            collapseOrExpandable();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(expandTextColor);
        }
    };

    public interface OnHighlightTextListener {
        void onNickName(String id, String name);
        void onBookName(String keyword);
    }
}
