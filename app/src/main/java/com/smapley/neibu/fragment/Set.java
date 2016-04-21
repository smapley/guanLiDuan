package com.smapley.neibu.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.activity.Gaimi;
import com.smapley.neibu.activity.Login;
import com.smapley.neibu.activity.ZongHuo;
import com.smapley.neibu.http.params.Reg2Params;
import com.smapley.neibu.http.service.GengxinService;
import com.smapley.neibu.http.service.Reg2Service;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

/**
 * Created by smapley on 16/2/28.
 */
@ContentView(R.layout.fragment_set)
public class Set extends Fragment {

    @ViewInject(R.id.title_item2)
    private TextView title_item2;
    @ViewInject(R.id.set_item1)
    private TextView set_item1;
    @ViewInject(R.id.set_item2)
    private TextView set_item2;
    @ViewInject(R.id.set_item3)
    private TextView set_item3;
    @ViewInject(R.id.set_item4)
    private TextView set_item4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initView();
        return view;
    }

    private void initView() {
        title_item2.setText("设置");
    }

    @Event({R.id.set_item1, R.id.set_item2, R.id.set_item3,R.id.set_item4})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_item1:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示：");
                builder.setMessage("是否退出登录？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reg2Service.load(new Reg2Params(MyData.UserName));

                    }
                });
                builder.create().show();
                break;
            case R.id.set_item2:
                gengxinService.load();
                break;
            case R.id.set_item3:
                startActivity(new Intent(getActivity(),Gaimi.class));
                break;
            case R.id.set_item4:
                startActivity(new Intent(getActivity(),ZongHuo.class));
                break;
        }
    }

    private Reg2Service reg2Service = new Reg2Service() {
        @Override
        public void Succ(String data) {
            SharedPreferences sp_user = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = sp_user.edit();
            editor.putBoolean("islogin", false);
            editor.commit();
            MyData.Login = false;
            startActivity(new Intent(getActivity(), Login.class));
            getActivity().finish();
        }
    };
    private GengxinService gengxinService = new GengxinService() {
        @Override
        public void Succ(String data) {
            Map<String, String> result = JSON.parseObject(data, new TypeReference<Map<String, String>>() {
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            if (Float.parseFloat(getVersion()) < Float.parseFloat(result.get("banben").toString())) {
                builder.setMessage("当前版本：" + getVersion() + "\n最新版本：" + result.get("banben").toString());
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("下载更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MyData.URL_xiazai));
                        startActivity(intent);
                    }
                });
            } else {
                builder.setMessage("当前已是最新版本:" + getVersion());
                builder.setNegativeButton("取消", null);
            }
            builder.create().show();
        }
    };

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
