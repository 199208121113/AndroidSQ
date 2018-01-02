package com.x.av.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.x.av.R;
import com.x.av.adapter.AVGuideAdapter;
import com.x.av.model.AVGuideItem;
import com.x.core.adapter.NewAdapterItem;
import com.x.core.base.BaseActivity;

import java.util.concurrent.atomic.AtomicInteger;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * @author xudafeng
 * 2018学习计划：http://blog.51cto.com/ticktick/p15
 * 1.Android平台使用AudioRecord和AudioTrack API完成音频PCM数据的采集和播放，并实现读写音频wav文件
 * 2.Android平台使用Camera API进行视频的采集，分别使用SurfaceView、TextureView来预览Camera数据，取到NV21的数据回调
 * 3.学习Android平台的MediaExtractor和MediaMuxer API，知道如何解析和封装MP4文件
 * 4.学习Android平台的OpenGL ES API，了解OpenGL开发的基本流程，使用OpenGL绘制一个三角形
 * 5.学习Android平台的OpenGL ES API,学习纹理绘制，使用OpenGL显示一张图片
 * 6.学习MediaCodec API，完成音频AAC的硬编、硬解
 * 7.学习MediaCodec API，完成视频H264的硬编、硬解
 * 8.串联整个音视频录制流程，完成音视频的采集、编码、封装成MP4输出
 * 9.串联整个音视频播放流程，完成MP4的解析、音视频的解码、播放和渲染
 * 10.进一步学OpenGL，了解如何实现视频的剪裁、旋转、水印、滤镜，并学习OpenGL的高级特性，如：VBO、VAO、FBO等等
 * 11.学习Android的图形图像架构，能够使用GLSurfaceview绘制Camera预览画面
 * 12.深入研究音视频相关的网络协议，如rtmp，hls，以及封包格式，如flv，MP4
 * 13.深入学习一些音视频领域的开源项目，如webrtc、ffmpeg、ijkplayer、librtmp等等
 * 14.将ffmpeg库一直到Android平台，结合上面积累的经验，编写一款简易的音视频播放器
 * 15.将x264库一直到Android平台，结合上面积累的经验，完成视频数据H264软编功能
 * 16.将librtmp库一直到Android平台，结合上面积累的经验完成rtmp推流的功能

 下面是一些推荐的参考资料：
     1. 《雷霄骅的专栏》：http://blog.csdn.net/leixiaohua1020
     2. 《Android音频开发》：http://ticktick.blog.51cto.com/823160/d-15
     3. 《FFMPEG Tips》：http://ticktick.blog.51cto.com/823160/d-17
     4. 《Learn OpenGL 中文》：https://learnopengl-cn.readthedocs.io/zh/latest/
     5. 《Android Graphic 架构》：https://source.android.com/devices/graphics/

 */
public class AVGuideActivity extends BaseActivity {

    private FamiliarRecyclerView mRecycleView;
    private AVGuideAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.act_av_guide_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecycleView = findViewById(R.id.act_av_guide_rc);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AVGuideAdapter(this);
        mRecycleView.setAdapter(adapter);
        loadItemData();

        mRecycleView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                if(position >= adapter.getItemCount() || position < 0)
                    return;
                NewAdapterItem item = adapter.getItem(position);
                if(!(item.getData() instanceof AVGuideItem)) {
                    return;
                }
                AVGuideItem itemData = (AVGuideItem) item.getData();
                Intent mIntent = null;
                if(itemData.getItemId() == ID_A_R_A_T_1) {
                    mIntent = AudioRecordAndAudioTrackActivity.createIntent(AVGuideActivity.this);
                }
                startActivity(mIntent);
            }
        });
    }

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    private static final int ID_A_R_A_T_1 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_2 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_3 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_4 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_5 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_6 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_7 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_8 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_9 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_10 = ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_11= ATOMIC_INTEGER.getAndIncrement();
    private static final int ID_A_R_A_T_12 = ATOMIC_INTEGER.getAndIncrement();
    private void loadItemData() {
        adapter.addItem(new AVGuideItem("AudioRecord和AudioTrack API完成音频采集和播放", ID_A_R_A_T_1), null);
        adapter.addItem(new AVGuideItem("Android平台使用Camera API进行视频的采集，分别使用SurfaceView、TextureView来预览Camera数据，取到NV21的数据回调", ID_A_R_A_T_2), null);
        adapter.addItem(new AVGuideItem("学习Android平台的MediaExtractor和MediaMuxer API，知道如何解析和封装MP4文件", ID_A_R_A_T_3), null);
        adapter.addItem(new AVGuideItem("学习Android平台的OpenGL ES API，了解OpenGL开发的基本流程，使用OpenGL绘制一个三角形", ID_A_R_A_T_4), null);
        adapter.addItem(new AVGuideItem("学习Android平台的OpenGL ES API,学习纹理绘制，使用OpenGL显示一张图片", ID_A_R_A_T_5), null);
        adapter.addItem(new AVGuideItem("学习MediaCodec API，完成音频AAC的硬编、硬解", ID_A_R_A_T_6), null);
        adapter.addItem(new AVGuideItem("学习MediaCodec API，完成视频H264的硬编、硬解", ID_A_R_A_T_7), null);
        adapter.addItem(new AVGuideItem("串联整个音视频录制流程，完成音视频的采集、编码、封装成MP4输出", ID_A_R_A_T_8), null);
        adapter.addItem(new AVGuideItem("串联整个音视频播放流程，完成MP4的解析、音视频的解码、播放和渲染", ID_A_R_A_T_9), null);
        adapter.addItem(new AVGuideItem("进一步学OpenGL，了解如何实现视频的剪裁、旋转、水印、滤镜，并学习OpenGL的高级特性，如：VBO、VAO、FBO等等", ID_A_R_A_T_10), null);
        adapter.addItem(new AVGuideItem("学习Android的图形图像架构，能够使用GLSurfaceview绘制Camera预览画面", ID_A_R_A_T_11), null);
        adapter.addItem(new AVGuideItem("深入研究音视频相关的网络协议，如rtmp，hls，以及封包格式，如flv，MP4", ID_A_R_A_T_12), null);

        adapter.notifyDataSetChanged();
    }
}
