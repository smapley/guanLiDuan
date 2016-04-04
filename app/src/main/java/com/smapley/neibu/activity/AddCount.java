package com.smapley.neibu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.http.params.Reg1Params;
import com.smapley.neibu.http.service.Reg1Service;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by smapley on 16/2/28.
 */
@ContentView(R.layout.activity_addcount)
public class AddCount extends Activity {

    @ViewInject(R.id.title_item2)
    private TextView title_item2;
    @ViewInject(R.id.title_item3)
    private TextView title_item3;

    @ViewInject(R.id.addcount_count)
    private EditText count;
    @ViewInject(R.id.addcount_password)
    private EditText password;
    @ViewInject(R.id.daili)
    private Button daili;
    @ViewInject(R.id.huiyuan)
    private Button huiyuan;

    private int type=0;

    private Reg1Service reg1Service=new Reg1Service() {
        @Override
        public void Succ(String data) {
            int result= JSON.parseObject(data,new TypeReference<Integer>(){});
            if(result==1){
                Toast.makeText(AddCount.this,"添加成功！",Toast.LENGTH_SHORT).show();
                MainActivity.getInstance().refresh();
                finish();
            }else {
                Toast.makeText(AddCount.this,"添加失败！",Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void upData() {
        reg1Service.load(new Reg1Params(MyData.UserName,count.getText().toString(),
                password.getText().toString(),type+""));
    }

    private void initView() {

        title_item2.setText("添加");
        title_item3.setText("确定");
    }

    @Event({R.id.title_item3, R.id.daili, R.id.huiyuan})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_item3:
                upData();
                break;
            case R.id.daili:
                daili.setBackgroundColor(getResources().getColor(R.color.chengse));
                huiyuan.setBackgroundColor(getResources().getColor(R.color.gray));
                type=1;
                break;
            case R.id.huiyuan:
                huiyuan.setBackgroundColor(getResources().getColor(R.color.chengse));
                daili.setBackgroundColor(getResources().getColor(R.color.gray));
                type=2;
                break;
        }
    }

}
