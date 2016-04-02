package com.smapley.neibu.http.service;


import com.smapley.neibu.http.callback.SimpleCallback;
import com.smapley.neibu.util.MyData;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class GengxinService {

    public void load() {

        x.http().post(new RequestParams(MyData.URL_gengxin), new SimpleCallback() {
            @Override
            public void Success(final String data) {
                Succ(data);
            }
        });
    }


    public abstract void Succ(String data);
}