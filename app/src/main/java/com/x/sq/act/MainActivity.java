package com.x.sq.act;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.x.sq.R;
import com.x.sq.adapter.MainVpAdapter;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class MainActivity extends SupperActivity{

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    private PageNavigationView pnvBottomTab;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vp = (ViewPager) findViewById(R.id.id_main_view_pager);
        vp.setAdapter(new MainVpAdapter(getSupportFragmentManager()));
        pnvBottomTab = (PageNavigationView) findViewById(R.id.id_main_pnv_tab);
        NavigationController nc = pnvBottomTab.material()
                .addItem(android.R.drawable.ic_menu_camera, "Note")
                .addItem(android.R.drawable.ic_menu_compass, "Blog")
                .addItem(android.R.drawable.ic_menu_search, "Search")
                .addItem(android.R.drawable.ic_menu_help, "Help")
                .build();
        nc.setupWithViewPager(vp);
    }


}
