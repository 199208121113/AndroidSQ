package com.x.sq.act;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.x.core.base.actionbar.ActionBarMenu;
import com.x.sq.R;

/**
 *  Drawable 知识点
 *          1.ColorDrawable
 *              a).Java定义
 *                  ColorDrawable drawable = new ColorDrawable(int colorId);
 *                  view.setBackground(drawable);
 *              b).xml中定义
 *                  <color xmlns:android = "http://schemas.android.com/apk/res/android"
 *                         android:color = "#ff0000"/>
 *              c).读取color.xml中的color值/使用系统自带的color Color.Black/Color.argb(0xff, 0x00, 0x00, 0x00);
 *
 *          2.NinePatchDrawable:.9图不能放在mipmap目录下，需要放在drawable目录下
 *
 *          3.ShapeDrawable:形状Drawable定义基本的几何图形
 *
 *          4.GradientDrawable:渐变Drawable 线性渐变/发散渐变/平铺渐变
 *              &startColor:起始颜色
 *              &centerColor:渐变的中间颜色
 *              &endColor:渐变的结束颜色
 *              &type:渐变类型（linear(线性),radial（发散）,sweep（平铺））
 *              &centerX:渐变中间颜色的X坐标，范围0-1
 *              &centerY:渐变的中间颜色Y坐标，范围0-1
 *              &angle:只有liner类型的渐变生效，表示渐变角度 必须为45的倍数
 *              &gradientRadius:只有radial和sweep类型的渐变才有效，radial必须设置，表示渐变效果的半径
 *              &userLevel是否根据level绘制渐变效果
 *
 *          5.BitmapDrawable 对Bitmap的一种封装，可以设置包装的bitmap在BitmapDrawable区域中的绘制方式：平铺填充/拉伸/原图 根节点<Bitmap />
 *              &src:资源
 *              &antialias:是否支持扛锯齿
 *              &filter:是否支持位图过滤
 *              &dither:是否对位图进行抖动处理
 *              &gravity:若位图比容器小，可以设置位图在容器中的相对位置
 *              &tileMode：指定图片平铺填充器模式，设置了这个gravity会被忽略,disable(拉伸平铺)clamp（原图大小）repeat（平铺）mirror（镜像平铺）
 *
 *         6.InsetDrawable 表示把一个Drawable嵌入到另外一个Drawable的内部，并且在内部留一些间距
 *              &drawable:引用的Drawable 如果为空，则必须有一个Drawable类型的子节点
 *              &visible:
 *              &insetLeft，insetRight,insetTop,insetBottom设置左右上下的边距
 *
 *         7.ClipDrawable 从位图上剪下一个部分
 *              &clipOrietntion:剪裁方向
 *              &gravity:剪裁的开始位置
 *              &drawable:资源图
 *
 *         8.rotateDrawable 旋转,通过setLevel来控制旋转maxValue = 10000
 *              &fromDegrees:其实角度，对应最低level值，默认0
 *              &toDegrees:结束角度，对应最高level值，默认360
 *              &pivotX:设置参照点的X坐标，取值为0-1，默认是50% 即0.5
 *              &pivotY:设置参照点的Y坐标，取值0-1，默认50%，即0.5
 *              &drawable：位图资源
 *              &visible:设置drawable是否可见
 *
 *         9.AnimationDrawable帧动画Drawable
 * Created by xudafeng on 2017/11/15.
 * @author xudafeng
 */

public class DrawableSummaryActivity extends SupperActivity {
    @Override
    protected int getContentView() {
        return R.layout.act_drawable_summary_layout;
    }

    @Override
    protected ActionBarMenu onActionBarCreate() {
        return new ActionBarMenu("Drawable小结");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
