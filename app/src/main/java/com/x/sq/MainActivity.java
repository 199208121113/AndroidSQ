package com.x.sq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.x.core.util.ToastUtil;
import com.x.sq.adapter.MainActAdapter;
import com.x.sq.model.ListItem;
import com.x.sq.model.StringItem;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    FamiliarRecyclerView mRecycleView;
    MainActAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (FamiliarRecyclerView)findViewById(R.id.mRecycleView);
        adapter = new MainActAdapter(this);
        mRecycleView.setAdapter(adapter);

        for (int i = 0; i < 30; i++) {
            adapter.addItem(new StringItem(""+(i + 1)), null);
        }

        List<StringItem> data = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            data.add(new StringItem(""+(i + 1)));
        }
        adapter.addItem(new ListItem(data), null);
        adapter.notifyDataSetChanged();

        mRecycleView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                String str = ((StringItem)adapter.getItem(position).getData()).getStr();
                ToastUtil.show(MainActivity.this, str);
            }
        });
    }
}
