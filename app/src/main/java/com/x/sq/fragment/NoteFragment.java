package com.x.sq.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.x.core.base.BaseFragment;
import com.x.sq.R;
import com.x.sq.act.GlideDemoActivity;
import com.x.sq.adapter.NoteAdapter;
import com.x.sq.model.MainItem;

import java.util.concurrent.atomic.AtomicInteger;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by xudafeng on 2017/11/10.
 */

public class NoteFragment extends BaseFragment implements FamiliarRecyclerView.OnItemClickListener{

    @Override
    protected int getContentView() {
        return R.layout.fg_note;
    }

    private FamiliarRecyclerView noteRecycleView;
    private NoteAdapter adapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteRecycleView = (FamiliarRecyclerView) view.findViewById(R.id.id_fg_note_recycle);
        adapter = new NoteAdapter(activity);
        adapter = new NoteAdapter(activity);
        noteRecycleView.setAdapter(adapter);
        noteRecycleView.setOnItemClickListener(this);
        loadData();
    }

    private static final AtomicInteger atomicInteger = new AtomicInteger(100);
    private static final int ID_HANDLE_IMG = atomicInteger.getAndIncrement();
    private static final int ID_DRAWABLE_SUMMARY = atomicInteger.getAndIncrement();
    private static final int ID_GLIDE_DEMO = atomicInteger.getAndIncrement();
    private static final int ID_AUDIO_AES = atomicInteger.getAndIncrement();
    private void loadData() {
        adapter.addItem(new MainItem(ID_HANDLE_IMG, "图片下载框架"), null);
        adapter.addItem(new MainItem(ID_DRAWABLE_SUMMARY, "Drawable总结"), null);
        adapter.addItem(new MainItem(ID_GLIDE_DEMO, "Glide Demo"), null);
        adapter.addItem(new MainItem(ID_AUDIO_AES, "加密音频文件"), null);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
        MainItem item = (MainItem) adapter.getItem(position).getData();
        if(item.getItemId() == ID_HANDLE_IMG) {

        }else if(item.getItemId() == ID_DRAWABLE_SUMMARY) {

        }else if(item.getItemId() == ID_GLIDE_DEMO) {
            activity.startActivity(GlideDemoActivity.createIntent(activity));
        }else if(item.getItemId() == ID_AUDIO_AES) {
            activity.startActivity(AudioAesDesTestActivity.createIntent(activity));
        }
    }
}
