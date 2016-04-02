package com.smapley.neibu.http.service;


import com.smapley.neibu.http.callback.SimpleCallback;
import com.smapley.neibu.http.params.GetGzhanghaoParams;

import org.xutils.x;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class GetGzhanghaoService {

    public void load(GetGzhanghaoParams params, final String name) {

        x.http().post(params, new SimpleCallback() {
            @Override
            public void Success(final String data) {
                Succ(data,name);
            }
        });
    }


    public abstract void Succ(String data,String name);
}