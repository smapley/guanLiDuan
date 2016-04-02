package com.smapley.neibu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.smapley.neibu.R;
import com.smapley.neibu.adapter.Main_Viewpage_Adapter;
import com.smapley.neibu.fragment.Main;
import com.smapley.neibu.fragment.Set;
import com.smapley.neibu.util.CustomViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    private Main mainFragment;
    private Set setFragment;

    @ViewInject(R.id.fragment)
    private CustomViewPager viewPager;

    private Main_Viewpage_Adapter viewpage_adapter;
    private List<Fragment> fragmentList;

    public static MainActivity mainActivity;

    public static MainActivity getInstance() {
        if (mainActivity == null) {
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        mainActivity=this;

    }

    private void initView() {
        fragmentList = new ArrayList<>();
        mainFragment = new Main();
        setFragment = new Set();
        fragmentList.add(mainFragment);
        fragmentList.add(setFragment);
        viewpage_adapter = new Main_Viewpage_Adapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewpage_adapter);

    }

    @Event({R.id.bottom_item1,R.id.bottom_item2})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.bottom_item1:
                viewPager.setCurrentItem(0);
                refresh();
                break;
            case R.id.bottom_item2:
                viewPager.setCurrentItem(1);
                break;
        }
    }



    public static void stop(){
        mainActivity.finish();
    }

    public void refresh(){
        mainFragment.getData();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
