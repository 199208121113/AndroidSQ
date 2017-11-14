package com.x.sq.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.x.sq.R;
import com.x.sq.adapter.NoteAdapter;
import com.x.sq.model.MainItem;

import java.util.concurrent.atomic.AtomicInteger;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by xudafeng on 2017/11/10.
 */

public class NoteFragment extends Fragment implements FamiliarRecyclerView.OnItemClickListener{
    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_note, container, false);
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
    private void loadData() {
        adapter.addItem(new MainItem(ID_HANDLE_IMG, "图片下载框架"), null);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
        MainItem item = (MainItem) adapter.getItem(position).getData();
        if(item.getItemId() == ID_HANDLE_IMG) {

        }
    }
}
